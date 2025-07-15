package br.com.systempus.systempus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.domain.HorarioAula;
import br.com.systempus.systempus.services.HorarioAulaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/horarioAula/")
@Tag(name = "HorarioAula")
//@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))(origins = ("*"), allowedHeaders = ("*"))
public class HorarioAulaController {

    @Autowired
    private HorarioAulaService service;


    @GetMapping("{id}")
    public ResponseEntity<HorarioAula> getOne(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<HorarioAula>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    // @PostMapping
    // public ResponseEntity<HorarioAula> save(@RequestBody HorarioAula horarioAula, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
    //     service.save(horarioAula);

    //     StringBuffer path = new StringBuffer();

    //     path.append(request.getRequestURI())
    //         .append("/")
    //         .append(horarioAula.getId());


    //     URI uri = new URI(path.toString());

    //     return ResponseEntity.created(uri).body(horarioAula);
    // }

    // @DeleteMapping("{id}")
    // public ResponseEntity<HorarioAula> delete(@PathVariable Integer id){
    //     service.deleteById(id);
    //     return ResponseEntity.noContent().build();
    // }

    // @PutMapping("{id}")
    // public ResponseEntity<HorarioAula> update(@RequestBody HorarioAula horarioAula, @PathVariable Integer id){
    //     service.update(horarioAula, id);
    //     return ResponseEntity.ok().body(horarioAula);
    // }

}
