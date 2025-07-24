package br.com.systempus.systempus.services;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.Coordenador;
import br.com.systempus.systempus.domain.Professor;
import br.com.systempus.systempus.domain.Profissional;
import br.com.systempus.systempus.domain.UserToken;
import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.domain.dto.CadastroDTO;
import br.com.systempus.systempus.domain.dto.CadastroProfissionalDTO;
import br.com.systempus.systempus.domain.dto.ProfissionalDTO;
import br.com.systempus.systempus.domain.enumerador.Status;
import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Service
public class CadastroService {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserTokenService tokenService;

    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private EmailService emailService;

    public Professor preCadastroProfessor(CadastroProfissionalDTO registro) {
        Professor professor = new Professor(
            registro.getNome(),
            registro.getCpf(),
            registro.getEmail(),
            cursoService.getCursosByIntegerList(registro.getCursos())
        );
        professorService.save(professor);
        return professor;
    }

    public Coordenador preCadastroCoordenador(CadastroProfissionalDTO registro) {
        Coordenador coordenador = new Coordenador(
            registro.getNome(),
            registro.getCpf(),
            registro.getEmail(),
            cursoService.getCursosByIntegerList(registro.getCursos())
        );
        coordenadorService.save(coordenador);
        return coordenador;
    }

    public Usuario preCadastroUsuario(Profissional profissional) {
        Usuario newUser = new Usuario(null, profissional.getEmail(), "", profissional);
        usuarioService.register(newUser);
        return newUser;
    }

    @Transactional
    public void enviarEmailCadastro(CadastroProfissionalDTO cadastro, Boolean isProfessor) throws MessagingException, IOException {
        Profissional profissional;
        String frontPath;
        if (isProfessor) {
            profissional = preCadastroProfessor(cadastro);
            frontPath = "cadastro/professor/";
        } else {
            profissional = preCadastroCoordenador(cadastro);
            frontPath = "cadastro/coordenador/";
        }
        Usuario newUser = preCadastroUsuario(profissional);
        UserToken tk = tokenService.save(newUser, LocalDateTime.now().plusHours(24));
        emailService.enviarEmailCadastro(cadastro.getEmail(), tk.getToken(), frontPath, profissional.getId());
    }

    @Transactional
    public void reenviarEmailCadastro(Integer idProfissional) throws MessagingException, IOException {
        String frontPath;
        Usuario user = usuarioService.getByIdProfissional(idProfissional);
        if (user.getProfissional().getClass() == Professor.class) {
            frontPath = "cadastro/professor/";
        } else {
            frontPath = "cadastro/coordenador/";
        }
        UserToken tk = tokenService.save(user, LocalDateTime.now().plusHours(24));
        emailService.enviarEmailCadastro(user.getProfissional().getEmail(), tk.getToken(), frontPath, idProfissional);
    }

    public ProfissionalDTO getProfessorById(String token, Integer idProfessor) {
        UserToken tk = tokenService.findByToken(token);
        tokenService.validateToken(tk);
        Professor p = professorService.getOne(idProfessor);
        return ProfissionalDTO.convertToDTO(p);
    }
    
    @Transactional
    public Professor cadastroProfessor(CadastroDTO professor) {
        UserToken tk = tokenService.findByToken(professor.getToken());
        tokenService.validateToken(tk);
        Professor p = professorService.getOne(professor.getId());
        p.setNome(professor.getNome());
        p.setEmail(professor.getEmail());
        p.setFoto(professor.getFoto());
        p.setStatusAtivacao(StatusAtivacao.ATIVO);
        p.setStatus(Status.ATIVO);
        p.setTelefone(professor.getTelefone());
        professorService.update(p);

        Usuario u = usuarioService.findUserByEmail(professor.getEmail())
                                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (u != null) {
            u.setProfissional(p);
            u.setUserName(p.getEmail());
            u.setPassword(professor.getSenha());
            usuarioService.resetPassword(u);
        }

        tk.setUsado(true);
        tokenService.updateToken(tk);
        return p;
    }

    @Transactional
    public Coordenador cadastroCoordenador(CadastroDTO coordenador) {
        UserToken tk = tokenService.findByToken(coordenador.getToken());
        tokenService.validateToken(tk);

        Coordenador c = coordenadorService.getOne(coordenador.getId());
        c.setNome(coordenador.getNome());
        c.setEmail(coordenador.getEmail());
        c.setFoto(coordenador.getFoto());
        c.setStatusAtivacao(StatusAtivacao.ATIVO);
        c.setStatus(Status.ATIVO);
        c.setTelefone(coordenador.getTelefone());
        coordenadorService.update(c);

        Usuario u = usuarioService.findUserByEmail(coordenador.getEmail())
                                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (u != null) {
            u.setProfissional(c);
            u.setUserName(c.getEmail());
            u.setPassword(coordenador.getSenha());
            usuarioService.resetPassword(u);
        }

        tk.setUsado(true);
        tokenService.updateToken(tk);
        return c;
    }

    public ProfissionalDTO getCoordenadorById(String token, Integer idCoordenador) {
        UserToken tk = tokenService.findByToken(token);
        tokenService.validateToken(tk);
        Coordenador c = coordenadorService.getOne(idCoordenador);
        return ProfissionalDTO.convertToDTO(c);
        
    }
}
