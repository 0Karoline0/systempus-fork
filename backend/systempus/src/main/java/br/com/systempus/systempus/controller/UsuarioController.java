package br.com.systempus.systempus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.services.UsuarioService;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {
    

    @Autowired
    private UsuarioService usuarioService;

    // @PostMapping("/cadastrar")
    // public ResponseEntity<Void> cadastrar() {

    // }

}
