package br.com.systempus.systempus.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import br.com.systempus.systempus.domain.Periodo;
import br.com.systempus.systempus.domain.dto.PeriodoDTO;
import br.com.systempus.systempus.services.PeriodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/periodo/")
@Tag(name = "Periodo")
//@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))(origins = ("*"), allowedHeaders = ("*"))
public class PeriodoController {

    @Autowired
    private PeriodoService service;


    @GetMapping("{id}")
    public ResponseEntity<PeriodoDTO> getOne(@PathVariable Integer id){
        return ResponseEntity.ok().body(PeriodoDTO.convertToDTO(service.getOne(id)));
    }

    @GetMapping
    public ResponseEntity<List<Periodo>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Periodo> save(@RequestBody Periodo periodo, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        service.save(periodo);

        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append("/")
            .append(periodo.getId());


        URI uri = new URI(path.toString());

        return ResponseEntity.created(uri).body(periodo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Periodo> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Periodo> update(@RequestBody Periodo periodo, @PathVariable Integer id){
        service.update(periodo, id);
        return ResponseEntity.ok().body(periodo);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Periodo> updatePartial(@RequestBody Map<String, Object> mapValores, @PathVariable Integer id){
        Periodo periodoAtualizada = service.updatePartial(mapValores, id);
        return ResponseEntity.ok().body(periodoAtualizada);
    }

    @GetMapping("curso/{idCurso}")
    public ResponseEntity<List<PeriodoDTO>> getPeriodosByCurso(@PathVariable Integer idCurso){
        List<Periodo> periodos = service.getPeriodosByCurso(idCurso);
        List<PeriodoDTO> periodosDTO = periodos.stream().map(periodo -> PeriodoDTO.convertToDTO(periodo)).collect(Collectors.toList());
        return ResponseEntity.ok().body(periodosDTO);
    }

}
