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

import br.com.systempus.systempus.domain.Curso;
import br.com.systempus.systempus.domain.Modulo;
import br.com.systempus.systempus.domain.Professor;
import br.com.systempus.systempus.services.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/curso/")
@Tag(name = "Curso")
@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("{id}")
    public ResponseEntity<Curso> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok().body(cursoService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> getAll() {
        return ResponseEntity.ok().body(cursoService.getAll());
    }


    @PostMapping
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
    public ResponseEntity<Curso> delete(@PathVariable Integer id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Curso> update(@Valid @RequestBody Curso curso) {
        cursoService.update(curso);
        return ResponseEntity.ok().body(curso);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Curso> upatePartial(@RequestBody Map<String, Object> mapValores, @PathVariable Integer id) {
        Curso cursoAtualizado = cursoService.updatePartial(mapValores, id);
        return ResponseEntity.ok().body(cursoAtualizado);
    }

    @PatchMapping("modulo/{idCurso}")
    public ResponseEntity<Curso> adicionarModulo(@PathVariable Integer idCurso, @RequestBody Modulo modulo){
        Curso cursoAtualizado = cursoService.adicionarModulo(idCurso, modulo);
        return ResponseEntity.ok().body(cursoAtualizado);
    }

    @GetMapping("professores/{idCurso}")
    public ResponseEntity<List<Professor>> getProfessoresByCurso(@PathVariable Integer idCurso){
        return ResponseEntity.ok().body(cursoService.getProfessoresByCurso(idCurso));
    }

}
