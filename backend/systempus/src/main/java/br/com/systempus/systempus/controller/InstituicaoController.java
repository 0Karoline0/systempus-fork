package br.com.systempus.systempus.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.domain.Instituicao;
import br.com.systempus.systempus.services.InstituicaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/instituicao/")
@Tag(name = "Instituição")
//@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))(origins = ("*"), allowedHeaders = ("*"))
public class InstituicaoController {

    @Autowired
    private InstituicaoService service;

    @GetMapping("{id}")
    public ResponseEntity<Instituicao> getOne(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.getOne(id));
    }

    @GetMapping
    //@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Instituicao>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Instituicao> save(@Valid @RequestBody Instituicao instituicao, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        service.save(instituicao);

        StringBuffer path = new StringBuffer();
        path.append(request.getRequestURI())
            .append("/")
            .append(instituicao.getId());

        URI uri = new URI(path.toString());
        return ResponseEntity.created(uri).body(instituicao);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Instituicao> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Instituicao> update(@RequestBody Instituicao instituicao){
        service.update(instituicao);
        return ResponseEntity.ok().body(instituicao);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Instituicao> updatePartial(@RequestBody Map<String, Object> mapValores, @PathVariable Integer id){
        Instituicao coordenaAtualizado = service.updatePartial(mapValores, id);
        return ResponseEntity.ok().body(coordenaAtualizado);
    }
}