package br.com.systempus.systempus.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.services.CadastroService;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("api/v1/cadastro")
public class CadastroController {
    
    @Autowired
    private CadastroService cadastroService;

    @PostMapping("/reenvio-email/{idProfissional}")
    public ResponseEntity<Void> reenviarEmailCadastro(@PathVariable Integer idProfissional) throws MessagingException, IOException {
        cadastroService.reenviarEmailCadastro(idProfissional);
        return ResponseEntity.ok().build();
    }


}
