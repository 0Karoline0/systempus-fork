package br.com.systempus.systempus.services;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.UserToken;
import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.domain.dto.CadastroDTO;
import br.com.systempus.systempus.domain.dto.CadastroProfissionalDTO;
import br.com.systempus.systempus.domain.dto.CursoDTO;
import br.com.systempus.systempus.domain.dto.ProfissionalDTO;
import br.com.systempus.systempus.domain.dto.professor.ProfessorDTO;
import br.com.systempus.systempus.domain.enumerador.ProfissionalEnum;
import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import br.com.systempus.systempus.domain.role_object.Coordenador;
import br.com.systempus.systempus.domain.role_object.Professor;
import br.com.systempus.systempus.domain.role_object.Profissional;
import br.com.systempus.systempus.domain.role_object.ProfissionalRole;
import br.com.systempus.systempus.services.role_object.ProfissionalRoleService;
import br.com.systempus.systempus.services.role_object.ProfissionalService;
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
    private ProfissionalService profissionalService;

    @Autowired
    private ProfissionalRoleService profissionalRoleService;

    @Autowired
    private EmailService emailService;

    public Profissional preCadastroProfessor(CadastroProfissionalDTO registro) {
        Profissional profissional = new Profissional();
        profissional.setNome(registro.getNome());
        profissional.setCpf(registro.getCpf());
        profissional.setEmail(registro.getEmail());
        profissional.setStatusAtivacao(StatusAtivacao.PENDENTE_ATIVACAO);
        profissionalService.save(profissional);

        profissionalRoleService.save(
            new ProfissionalRole(
                null,
                ProfissionalEnum.PROFESSOR,
                profissional
            )
        );

        Professor professor = new Professor();
        professor.setCursosLecionados(cursoService.getCursosByIntegerList(registro.getCursos()));
        professor.setProfissional(profissional);
        professorService.save(professor);

        return profissional;
    }

    public Profissional preCadastroCoordenador(CadastroProfissionalDTO registro) {
        Profissional profissional = new Profissional();
        profissional.setNome(registro.getNome());
        profissional.setCpf(registro.getCpf());
        profissional.setEmail(registro.getEmail());
        profissional.setStatusAtivacao(StatusAtivacao.PENDENTE_ATIVACAO);
        profissionalService.save(profissional);

        profissionalRoleService.save(
            new ProfissionalRole(
                null,
                ProfissionalEnum.COORDENADOR,
                profissional
            )
        );

        Coordenador coordenador = new Coordenador();
        coordenador.setCursosGerenciados(cursoService.getCursosByIntegerList(registro.getCursos()));
        coordenador.setProfissional(profissional);
        coordenadorService.save(coordenador);
        return profissional;
    }

    public Usuario preCadastroUsuario(Profissional profissional, boolean isProfessor) {
        Usuario newUser = new Usuario(null, profissional.getEmail(), "", profissional  /*, null*/);
        // Set<Role> roles = new HashSet<>();
        // if (isProfessor) {
        //     newUser.getProfissional().addRole(ProfissionalRole);
        // } else {

        // }
        // if (isProfessor) {
        //     roles.add(Role.PROFESSOR);
        //     newUser.setRoles(roles);
        // } else {
        //     roles.add(Role.COORDENADOR);
        //     newUser.setRoles(roles);
        // }
        usuarioService.register(newUser);
        return newUser;
    }

    @Transactional
    public void enviarEmailCadastro(CadastroProfissionalDTO cadastro, Boolean isProfessor) throws MessagingException, IOException {
        Profissional profissional;
        String frontPath = "";
        if (isProfessor) {
            profissional = preCadastroProfessor(cadastro);
            frontPath = "cadastro/professor/";
        } else {
            profissional = preCadastroCoordenador(cadastro);
            frontPath = "cadastro/coordenador/";
        }

        Usuario newUser = preCadastroUsuario(profissional, isProfessor);
        UserToken tk = tokenService.save(newUser, LocalDateTime.now().plusHours(24));
        emailService.enviarEmailCadastro(cadastro.getEmail(), tk.getToken(), frontPath, profissional.getId());
    }

    @Transactional
    public void reenviarEmailCadastro(Integer idProfissional, boolean isProfessor) throws MessagingException, IOException {
        String frontPath;
        Usuario user = usuarioService.getByIdProfissional(idProfissional);
        if (isProfessor) {
            frontPath = "cadastro/professor/";
        } else {
            frontPath = "cadastro/coordenador/";
        }
        UserToken tk = tokenService.save(user, LocalDateTime.now().plusHours(24));
        emailService.enviarEmailCadastro(user.getProfissional().getEmail(), tk.getToken(), frontPath, idProfissional);
    }

    public ProfissionalDTO getProfessorById(String token, Integer idProfissional) {
        UserToken tk = tokenService.findByToken(token);
        tokenService.validateToken(tk);
        Professor p = professorService.getOne(idProfissional);
        return ProfissionalDTO.convertToDTO(p);
    }
    
    @Transactional
    public Professor cadastroProfessor(CadastroDTO professor) {
        UserToken tk = tokenService.findByToken(professor.getToken());
        tokenService.validateToken(tk);
        Professor p = professorService.getOne(professor.getId());
        p.getProfissional().setNome(professor.getNome());
        p.getProfissional().setEmail(professor.getEmail());
        p.getProfissional().setFoto(professor.getFoto());
        p.getProfissional().setTelefone(professor.getTelefone());
        p.getProfissional().setStatusAtivacao(StatusAtivacao.ATIVO);

        ProfessorDTO pDto = ProfessorDTO.convertToDTO(p.getProfissional());
        professorService.update(pDto);

        Usuario u = usuarioService.findUserByEmail(professor.getEmail());
        
        if (u != null) {
            u.setProfissional(p.getProfissional());
            u.setUsername(p.getProfissional().getEmail());
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
        c.getProfissional().setNome(coordenador.getNome());
        c.getProfissional().setEmail(coordenador.getEmail());
        c.getProfissional().setFoto(coordenador.getFoto());
        c.getProfissional().setStatusAtivacao(StatusAtivacao.ATIVO);
        c.getProfissional().setTelefone(coordenador.getTelefone());
        Coordenador salvo = coordenadorService.update(c);

        Usuario u = usuarioService.findUserByEmail(coordenador.getEmail());

        if (u != null) {
            u.setProfissional(salvo.getProfissional());
            u.setUsername(c.getProfissional().getEmail());
            u.setPassword(coordenador.getSenha());
            usuarioService.resetPassword(u);
        }

        tk.setUsado(true);
        tokenService.updateToken(tk);
        return c;
    }

    public ProfissionalDTO getCoordenadorById(Integer idProfissional, JwtAuthenticationToken token) {
        UserToken tk = tokenService.findByToken(token.getToken().getTokenValue());
        tokenService.validateToken(tk);
        Coordenador c = coordenadorService.getOne(idProfissional);
        return new ProfissionalDTO(
            c.getProfissional().getId(),
            c.getProfissional().getCpf(),
            c.getProfissional().getNome(),
            c.getProfissional().getTelefone(),
            c.getProfissional().getEmail(),
            c.getProfissional().getStatusAtivacao(),
            c.getProfissional().getFoto(),
            CursoDTO.convertToDTO(c.getCursosGerenciados())
        );
    }
}





// ================================== PERTENCIA AO PROFESSOR E FOI AJUSTADO (EU ACHO!) <===========================

    // public void save(ProfessorDTO professor) {
    //     Profissional p = new Profissional();
    //     p.setNome(professor.getNome());
    //     p.setCpf(professor.getCpf());
    //     p.setEmail(professor.getEmail());
    //     p.setFoto(professor.getFoto());
    //     p.setTelefone(professor.getTelefone());
    //     Profissional cadastrado = profissionalService.save(p);

    //     ProfissionalRole role = profissionalRoleService.getByProfissionalEnum(ProfissionalEnum.PROFESSOR);
    //     role.getProfissionais().add(cadastrado);
    //     profissionalRoleService.update(role);

    //     Professor p2 = (Professor) cadastrado.getByRole(ProfissionalEnum.PROFESSOR);
    //     p2.setCursosLecionados(
    //         professor.getCursosLecionados().stream().map(
    //              c -> cursoRepository.findById(c.getId()).get()
    //     ).collect(Collectors.toList()));
    //     repository.save(p2);
    // }