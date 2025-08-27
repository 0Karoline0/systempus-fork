package br.com.systempus.systempus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.domain.Profissional;
import br.com.systempus.systempus.domain.dto.DisciplinaDTO;
import br.com.systempus.systempus.services.UsuarioService;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> getProfissionalByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(usuarioService.getProfissionalByUserId(id));
    }

    @GetMapping("/{id}/preferem/disciplinas")
    public ResponseEntity<List<DisciplinaDTO>> getDisciplinasPreferidasByUsuario(@PathVariable Integer id){
        return ResponseEntity.ok().body(usuarioService.getDisciplinasPreferidas(id));
    }

}
