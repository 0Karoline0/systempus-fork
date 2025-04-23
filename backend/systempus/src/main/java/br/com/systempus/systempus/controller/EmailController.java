package br.com.systempus.systempus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.services.EmailService;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // @PostMapping("/enviar")
    // public String enviarEmail(@RequestParam String para, @RequestParam String assunto, @RequestParam String mensagem){
    //     emailService.enviarEmailSimples(para, assunto, mensagem);
    //     return "Email enviado com sucesso!";
    // }

}
