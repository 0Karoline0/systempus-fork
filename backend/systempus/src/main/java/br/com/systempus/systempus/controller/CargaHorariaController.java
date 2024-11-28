package br.com.systempus.systempus.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.domain.CargaHoraria;
import br.com.systempus.systempus.services.CargaHorariaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/cargaHoraria/")
@Tag(name = "CargaHoraria")
@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))
public class CargaHorariaController {

    @Autowired
    private CargaHorariaService service;


    @GetMapping("{id}")
    public ResponseEntity<CargaHoraria> getOne(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<CargaHoraria>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<CargaHoraria> save(@RequestBody CargaHoraria cargaHoraria, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        service.save(cargaHoraria);

        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append("/")
            .append(cargaHoraria.getId());


        URI uri = new URI(path.toString());

        return ResponseEntity.created(uri).body(cargaHoraria);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CargaHoraria> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CargaHoraria> update(@RequestBody CargaHoraria cargaHoraria, @PathVariable Integer id){
        service.update(cargaHoraria);
        return ResponseEntity.ok().body(cargaHoraria);
    }

}
