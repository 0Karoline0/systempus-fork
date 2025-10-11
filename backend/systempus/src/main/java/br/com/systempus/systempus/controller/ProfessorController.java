package br.com.systempus.systempus.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.domain.dto.CadastroDTO;
import br.com.systempus.systempus.domain.dto.CadastroProfissionalDTO;
import br.com.systempus.systempus.domain.dto.DisciplinaDTO;
import br.com.systempus.systempus.domain.dto.DisponibilidadeProfessorDTO;
import br.com.systempus.systempus.domain.dto.ProfessorCompatibilidadeDTO;
import br.com.systempus.systempus.domain.dto.ProfissionalDTO;
import br.com.systempus.systempus.domain.dto.professor.ProfessorDTO;
import br.com.systempus.systempus.domain.role_object.Professor;
import br.com.systempus.systempus.domain.role_object.Profissional;
import br.com.systempus.systempus.services.CadastroService;
import br.com.systempus.systempus.services.DisponibilidadeProfessorService;
// import br.com.systempus.systempus.services.CadastroService;
// import br.com.systempus.systempus.services.DisponibilidadeProfessorService;
import br.com.systempus.systempus.services.ProfessorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/professor")
@Tag(name = "Professor")
//@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))(origins = ("*"), allowedHeaders = ("*"))
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private DisponibilidadeProfessorService disponibilidadesService;

    @Autowired
    private CadastroService cadastroService;

    // Pegar professor por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR') or #id == authentication.principal.id")
    public ResponseEntity<ProfessorDTO> getOne(@PathVariable Integer id) {
        ProfessorDTO p = ProfessorDTO.convertToDTO(professorService.getOne(id).getProfissional());
        return ResponseEntity.ok().body(p);
    }
    
    // Pegar professor por ID
    @GetMapping("/{token}/{idProfessor}")
    @PreAuthorize("hasRole('COORDENADOR') or #id == authentication.principal.id")
    public ResponseEntity<ProfissionalDTO> getProfessorById(@PathVariable String token, @PathVariable Integer idProfessor){
        ProfissionalDTO professor = cadastroService.getProfessorById(token, idProfessor);
        return ResponseEntity.ok().body(professor);
    }
    
    // Listar todos os professores
    @PreAuthorize("hasRole('COORDENADOR')")
    @GetMapping("/")
    public ResponseEntity<List<ProfessorDTO>> getAll(){
        return ResponseEntity.ok().body(professorService.getAll());
    }
    
    // Pré-cadastro feito na parte de dentro do sistema pelo coordenador, que também enviará o email de cadastro completo para o professor
    @PostMapping("/pre-cadastro")
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<Void> enviarEmailCadastro(@RequestBody CadastroProfissionalDTO cadastro) throws MessagingException, IOException {
        cadastroService.enviarEmailCadastro(cadastro, true);
        return ResponseEntity.ok().build();
    }  

    // Cadastro feito pelo professor depois de receber o email que levará à página de cadastro
    @PostMapping("/cadastro")
    public ResponseEntity<Professor> save(@RequestBody CadastroDTO professor, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        Professor p = cadastroService.cadastroProfessor(professor);

        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append("/")
            .append(p.getId());

        URI uri = new URI(path.toString());

        return ResponseEntity.created(uri).body(p);
    }

    // Deleta um professor -> mas não tira ele do sistema como profissional, apenas tira o papel de professor dele
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENADOR', 'ADM')")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Atualiza os dados de um professor com update
    @PreAuthorize("hasRole(PROFESSOR) and #id == authentication.principal.id")
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> update(@RequestBody ProfessorDTO professor){
        professorService.update(professor);
        return ResponseEntity.ok().body(professor);
    }

    // Atualiza parcialmente os dados de um professor
    @PreAuthorize("hasRole('PROFESSOR') and #id == authentication.principal.id")
    @PatchMapping("/{id}")
    public ResponseEntity<ProfessorDTO> updatePartial(@RequestBody Map<String, Object> mapValores, @PathVariable Integer id){
        ProfessorDTO professorAtualizado = professorService.updatePartial(mapValores, id);
        return ResponseEntity.ok().body(professorAtualizado);
    }

    // Salva as disponibilidades de um professor
    @PreAuthorize("hasRole('PROFESSOR') and #id == authentication.principal.id")
    @PostMapping("/{id}/disponibilidades")
    public ResponseEntity<List<DisponibilidadeProfessorDTO>> saveDisponibilidadesPorProfessor(@PathVariable Integer id, @RequestBody List<DisponibilidadeProfessorDTO> disponibilidadeRequest, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        List<DisponibilidadeProfessorDTO> disponibilidades = disponibilidadesService.saveDisponibilidadeProfessor(disponibilidadeRequest, id);

        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append("/")
            .append(id);

        URI uri = new URI(path.toString());

        return ResponseEntity.created(uri).body(disponibilidades);
    }

    // Atualiza as disponibilidades de um determinado professor
    @PreAuthorize("hasRole('PROFESSOR') and #id == authentication.principal.id")
    @PutMapping("/{id}/disponibilidades")
    public ResponseEntity<List<DisponibilidadeProfessorDTO>> updateDisponibilidadesPorProfessor(@PathVariable Integer id, @RequestBody List<DisponibilidadeProfessorDTO> disponibilidades) {
        List<DisponibilidadeProfessorDTO> disponibilidadesSalvas = disponibilidadesService.updateDisponibilidadeProfessor(id, disponibilidades);
        return ResponseEntity.ok().body(disponibilidadesSalvas);
    }

    // Pega a disponibilidade de horários de um determinado professor    
    @PreAuthorize("hasRole('COORDENADOR') or #id == authentication.principal.id")
    @GetMapping("/{id}/disponibilidades")
    public ResponseEntity<List<DisponibilidadeProfessorDTO>> getDisponibilidadeByProfessorId(@PathVariable Integer id){
        List<DisponibilidadeProfessorDTO> disponibilidades = professorService.getDisponibilidadeByProfessorId(id);
        return ResponseEntity.ok().body(disponibilidades);
    }

    // Salva as disciplinas favoritas de um professor
    @PreAuthorize("hasRole('PROFESSOR') and #id == authentication.principal.id")
    @PostMapping("/{id}/preferem/disciplinas")
    public ResponseEntity<Professor> salvarDisponibilidadesPreferidas(@PathVariable Integer id, @RequestBody List<Integer> disciplinasIds, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        Professor professor = professorService.salvarDisciplinasPreferidas(id, disciplinasIds);
        
        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append('/')
            .append(id);

        URI uri = new URI(path.toString());
        
        return ResponseEntity.created(uri).body(professor);
    }

    
    // Lista as disciplinas favoritas de um professor
    @PreAuthorize("hasRole('PROFESSOR') and #id == authentication.principal.id")
    @GetMapping("/{id}/preferem/disciplinas")
    public ResponseEntity<List<DisciplinaDTO>> getDisciplinasPreferidasByProfessor(@PathVariable Integer id){
        List<DisciplinaDTO> disciplinas = DisciplinaDTO.convertToDTO(professorService.getDisciplinasPreferidas(id).stream().toList());
        return ResponseEntity.ok().body(disciplinas);
    }

    // Lista professores com compatibilidade para cada disciplina
    @PreAuthorize("hasRole('COORDENADOR')")
    @GetMapping("/{idDisciplina}/disciplina/compatibilidade")
    public ResponseEntity<List<ProfessorCompatibilidadeDTO>> getProfessoresComCompatibilidade(@PathVariable Integer idDisciplina){
        return ResponseEntity.ok().body(professorService.getProfessoresPorCompatibilidade(idDisciplina));
    }

    // @PatchMapping("/status/{id}")
    // public ResponseEntity<Professor> updateStatusAtivacao(@PathVariable Integer id, @RequestBody Map<String, Integer> status){
        // Professor professorAtualizado = professorService.changeProfessorStatusAtivacao(id, status);
        // return ResponseEntity.ok().body(professorAtualizado);
    // }

}