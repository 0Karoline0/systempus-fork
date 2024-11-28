package br.com.systempus.systempus.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.domain.HorarioDisciplina;
import br.com.systempus.systempus.domain.dto.HorarioDisciplinaDTO;
import br.com.systempus.systempus.services.HorarioDisciplinaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/horarioDisciplina/")
@Tag(name = "HorarioDisciplina")
@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))
public class HorarioDisciplinaController {

    @Autowired
    private HorarioDisciplinaService service;


    // @GetMapping("{id}")
    // public ResponseEntity<HorarioDisciplina> getOne(@PathVariable Integer id){
    //     return ResponseEntity.ok().body(service.getById(id));
    // }

    @GetMapping
    public ResponseEntity<List<HorarioDisciplinaDTO>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

//    @PostMapping
//    public ResponseEntity<HorarioDisciplina> save(@RequestBody HorarioDisciplina horarioDisciplina, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
//        service.save(horarioDisciplina);
//
//        StringBuffer path = new StringBuffer();
//
//        path.append(request.getRequestURI())
//            .append("/")
//            .append(horarioDisciplina.getId());
//
//
//        URI uri = new URI(path.toString());
//
//        return ResponseEntity.created(uri).body(horarioDisciplina);
//    }

    // @DeleteMapping("{id}")
    // public ResponseEntity<HorarioDisciplina> delete(@PathVariable Integer id){
    //     service.deleteById(id);
    //     return ResponseEntity.noContent().build();
    // }

    // @PutMapping("{id}")
    // public ResponseEntity<HorarioDisciplina> update(@RequestBody HorarioDisciplina horarioDisciplina, @PathVariable Integer id){
    //     service.update(horarioDisciplina, id);
    //     return ResponseEntity.ok().body(horarioDisciplina);
    // }

}
