package br.com.systempus.systempus.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.Professor;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ProfessorService professorService;

    public void enviarEmailAlteracaoHorario(Integer idProfessor, Integer idDisciplina, String mensagemCustomizada) throws IOException, MessagingException{
        Professor p = professorService.getOne(idProfessor);
        Disciplina d = disciplinaService.getOne(idDisciplina);

        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");

        helper.setTo("karolinevmfaria@gmail.com");
        helper.setSubject("Solicitação de Alocamento de Horário");

        ClassPathResource resource = new ClassPathResource("messages/alocamento_horario.html");
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        html = html
            .replace("{{NOME_PROFESSOR}}", "Lasanha")
            .replace("{{NOME_DISCIPLINA}}", "Batata Frita")
            .replace("{{MODULO}}", "AB-1")
            .replace("{{CURSO}}", "Culinária")
            .replace("{{MENSAGEM_ADICIONAL}}", "TESTE");
        
        helper.setText(html, true);
        mailSender.send(mime);
    }

}
