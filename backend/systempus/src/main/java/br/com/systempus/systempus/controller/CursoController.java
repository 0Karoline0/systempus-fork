package br.com.systempus.systempus.controller;

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

import br.com.systempus.systempus.domain.Curso;
import br.com.systempus.systempus.domain.Modulo;
import br.com.systempus.systempus.domain.dto.CursoDTO;
import br.com.systempus.systempus.domain.role_object.Professor;
import br.com.systempus.systempus.services.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/curso/")
@Tag(name = "Curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("{id}")
    public ResponseEntity<CursoDTO> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok().body(CursoDTO.convertToDTO(cursoService.getOne(id)));
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAll() {
        return ResponseEntity.ok().body(cursoService.getAll());
    }

    @GetMapping("cursos")
    public ResponseEntity<List<CursoDTO>> getCursos() {
        return ResponseEntity.ok().body(cursoService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_COORDENADOR', 'SCOPE_ADM')")
    public ResponseEntity<Curso> save(@Valid @RequestBody Curso curso, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        cursoService.save(curso);

        StringBuffer path = new StringBuffer();
        path.append(request.getRequestURI())
            .append("/")
            .append(curso.getId());

        URI uri = new URI(path.toString());
        return ResponseEntity.created(uri).body(curso);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_COORDENADOR', 'SCOPE_ADM')")
    public ResponseEntity<Curso> delete(@PathVariable Integer id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_COORDENADOR', 'SCOPE_ADM')")
    public ResponseEntity<Curso> update(@Valid @RequestBody Curso curso) {
        cursoService.update(curso);
        return ResponseEntity.ok().body(curso);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_COORDENADOR', 'SCOPE_ADM')")
    public ResponseEntity<Curso> upatePartial(@RequestBody Map<String, Object> mapValores, @PathVariable Integer id) {
        Curso cursoAtualizado = cursoService.updatePartial(mapValores, id);
        return ResponseEntity.ok().body(cursoAtualizado);
    }

    @PatchMapping("modulo/{idCurso}")
    @PreAuthorize("hasAnyAuthority('SCOPE_COORDENADOR', 'SCOPE_ADM')")
    public ResponseEntity<Curso> adicionarModulo(@PathVariable Integer idCurso, @RequestBody Modulo modulo){
        Curso cursoAtualizado = cursoService.adicionarModulo(idCurso, modulo);
        return ResponseEntity.ok().body(cursoAtualizado);
    }
    
    @GetMapping("professores/{idCurso}")
    @PreAuthorize("hasAnyAuthority('SCOPE_COORDENADOR', 'SCOPE_ADM')")
    public ResponseEntity<List<Professor>> getProfessoresByCurso(@PathVariable Integer idCurso){
        return ResponseEntity.ok().body(cursoService.getProfessoresByCurso(idCurso));
    }
    
    @GetMapping("{idCurso}/professores/sem-horarios")
    @PreAuthorize("hasAnyAuthority('SCOPE_COORDENADOR', 'SCOPE_ADM')")
    public ResponseEntity<List<Professor>> getProfessorSemHorarios(@PathVariable Integer idCurso){
        List<Professor> professores = cursoService.getProfessoresSemHorarios(idCurso);
        return ResponseEntity.ok().body(professores);
    }

    @GetMapping("/professor/{idProfissional}")
    @PreAuthorize("hasAnyAuthority('SCOPE_COORDENADOR', 'SCOPE_ADM') or #idProfissional.toString() == authentication.principal.claims['sub']")
    public ResponseEntity<List<CursoDTO>> getCursosByProfessor(@PathVariable Integer idProfissional){
        List<CursoDTO> cursos = cursoService.getCursosByProfessor(idProfissional);
        return ResponseEntity.ok().body(cursos);
    }

}
