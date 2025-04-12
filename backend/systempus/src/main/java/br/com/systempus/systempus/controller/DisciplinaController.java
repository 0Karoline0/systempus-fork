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

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.dto.DisciplinaDTO;
import br.com.systempus.systempus.domain.dto.HorarioDisciplinaDTO;
import br.com.systempus.systempus.services.DisciplinaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/disciplina/")
@Tag(name = "Disciplina")
@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))
public class DisciplinaController {

    @Autowired
    private DisciplinaService service;

    @GetMapping("{id}")
    public ResponseEntity<DisciplinaDTO> getOne(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.getDisciplinaById(id));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Disciplina> save(@RequestBody Disciplina disciplina, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        service.save(disciplina);

        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append("/")
            .append(disciplina.getId());


        URI uri = new URI(path.toString());

        return ResponseEntity.created(uri).body(disciplina);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Disciplina> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Disciplina> update(@RequestBody Disciplina disciplina){
        service.update(disciplina);
        return ResponseEntity.ok().body(disciplina);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Disciplina> updatePartial(@RequestBody Map<String, Object> mapValores, @PathVariable Integer id){
        Disciplina disciplinaAtualizada = service.updatePartial(mapValores, id);
        return ResponseEntity.ok().body(disciplinaAtualizada);
    }

    @GetMapping("curso/{idCurso}")
    public ResponseEntity<List<DisciplinaDTO>> getByCurso(@PathVariable Integer idCurso){
        return ResponseEntity.ok().body(service.getByCurso(idCurso));
    }
    
    @GetMapping("{id}/horarios")
    public ResponseEntity<List<HorarioDisciplinaDTO>> getDisponibilidadeByProfessorId(@PathVariable Integer id){
        List<HorarioDisciplinaDTO> horarios = service.getHorariosByDisciplina(id);
        return ResponseEntity.ok().body(horarios);
    }
    
    @PostMapping("{id}/horarios")
    public ResponseEntity<List<HorarioDisciplinaDTO>> saveHorariosDisciplina(@PathVariable Integer id, @RequestBody List<HorarioDisciplinaDTO> disponibilidadeRequest, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        List<HorarioDisciplinaDTO> horarios = service.saveHorariosDisciplina(disponibilidadeRequest, id);

        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append("/")
            .append(id);

        URI uri = new URI(path.toString());

        return ResponseEntity.created(uri).body(horarios);
    }

    @PutMapping("{id}/horarios")
    public ResponseEntity<List<HorarioDisciplinaDTO>> updateDisponibilidadesPorProfessor(@PathVariable Integer id, @RequestBody List<HorarioDisciplinaDTO> disponibilidades) {
        List<HorarioDisciplinaDTO> horariosSalvos = service.updateHorariosDisciplina(id, disponibilidades);
        return ResponseEntity.ok().body(horariosSalvos);
    }


}
