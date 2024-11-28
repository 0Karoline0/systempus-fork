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

import br.com.systempus.systempus.domain.Coordenador;
import br.com.systempus.systempus.services.interfaces.ICoordenadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/coordenador/")
@Tag(name = "Coordenador")
@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))
public class CoordenadorController {

    @Autowired
    private ICoordenadorService coordenadorService;

    @GetMapping("{id}")
    public ResponseEntity<Coordenador> getOne(@PathVariable Integer id){
        return ResponseEntity.ok().body(coordenadorService.getOne(id));
    }

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Coordenador>> getAll(){
        return ResponseEntity.ok().body(coordenadorService.getAll());
    }

    @PostMapping
    public ResponseEntity<Coordenador> save(@Valid @RequestBody Coordenador coordenador, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        coordenadorService.save(coordenador);

        StringBuffer path = new StringBuffer();
        path.append(request.getRequestURI())
            .append("/")
            .append(coordenador.getId());

        URI uri = new URI(path.toString());
        return ResponseEntity.created(uri).body(coordenador);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Coordenador> delete(@PathVariable Integer id){
        coordenadorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Coordenador> update(@RequestBody Coordenador coordenador){
        coordenadorService.update(coordenador);
        return ResponseEntity.ok().body(coordenador);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Coordenador> updatePartial(@RequestBody Map<String, Object> mapValores, @PathVariable Integer id){
        Coordenador coordenaAtualizado = coordenadorService.updatePartial(mapValores, id);
        return ResponseEntity.ok().body(coordenaAtualizado);
    }
}