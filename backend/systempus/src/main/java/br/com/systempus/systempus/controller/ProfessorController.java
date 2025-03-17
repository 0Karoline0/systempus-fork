package br.com.systempus.systempus.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.domain.Professor;
import br.com.systempus.systempus.domain.dto.DisciplinaDTO;
import br.com.systempus.systempus.domain.dto.DisponibilidadeProfessorDTO;
import br.com.systempus.systempus.domain.dto.ProfessorCompatibilidadeDTO;
import br.com.systempus.systempus.services.ProfessorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/professor")
@Tag(name = "Professor")
@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getOne(@PathVariable Integer id){
        return ResponseEntity.ok().body(professorService.getOne(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Professor>> getAll(){
        return ResponseEntity.ok().body(professorService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Professor> save(@RequestBody Professor professor, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        professorService.save(professor);

        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append("/")
            .append(professor.getId());

        URI uri = new URI(path.toString());

        return ResponseEntity.created(uri).body(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Professor> delete(@PathVariable Integer id){
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<Professor> update(@RequestBody Professor professor){
        professorService.update(professor);
        return ResponseEntity.ok().body(professor);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Professor> updatePartial(@RequestBody Map<String, Object> mapValores, @PathVariable Integer id){
        Professor professorAtualizado = professorService.updatePartial(mapValores, id);
        return ResponseEntity.ok().body(professorAtualizado);
    }

    @PostMapping("/{id}/disponibilidades")
    public ResponseEntity<List<DisponibilidadeProfessorDTO>> saveDisponibilidadesPorProfessor(@PathVariable Integer id, @RequestBody List<DisponibilidadeProfessorDTO> disponibilidadeRequest, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        List<DisponibilidadeProfessorDTO> disponibilidades = professorService.saveDisponibilidades(disponibilidadeRequest, id);

        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append("/")
            .append(id);

        URI uri = new URI(path.toString());

        return ResponseEntity.created(uri).body(disponibilidades);
    }

    @PutMapping("/{id}/disponibilidades")
    public ResponseEntity<List<DisponibilidadeProfessorDTO>> updateDisponibilidadesPorProfessor(@PathVariable Integer id, @RequestBody List<DisponibilidadeProfessorDTO> disponibilidades) {
        List<DisponibilidadeProfessorDTO> disponibilidadesSalvas = professorService.updateDisponibilidadeProfessor(id, disponibilidades);
        return ResponseEntity.ok().body(disponibilidadesSalvas);
    }

    @GetMapping("/{id}/disponibilidades")
    public ResponseEntity<List<DisponibilidadeProfessorDTO>> getDisponibilidadeByProfessorId(@PathVariable Integer id){
        List<DisponibilidadeProfessorDTO> disponibilidades = professorService.getDisponibilidadeByProfessorId(id);
        return ResponseEntity.ok().body(disponibilidades);
    }

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

    
    @GetMapping("/{id}/preferem/disciplinas")
    public ResponseEntity<List<DisciplinaDTO>> getDisciplinasPreferidasByProfessor(@PathVariable Integer id){
        return ResponseEntity.ok().body(professorService.getDisciplinasPreferidas(id));
    }

    @GetMapping("/{idDisciplina}/disciplina/compatibilidade")
    public ResponseEntity<List<ProfessorCompatibilidadeDTO>> getProfessoresComCompatibilidade(@PathVariable Integer idDisciplina){
        return ResponseEntity.ok().body(professorService.getProfessoresPorCompatibilidade(idDisciplina));
    }

}