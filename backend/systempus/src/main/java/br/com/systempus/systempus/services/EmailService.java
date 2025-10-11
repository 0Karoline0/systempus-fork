package br.com.systempus.systempus.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.role_object.Professor;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailAlteracaoHorario(Professor professor, Disciplina disciplina, String mensagemCustomizada) throws IOException, MessagingException{

        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");

        helper.setTo("karolinevmfaria@gmail.com");//roussian23@gmail.com
        helper.setSubject("Solicitação de Alocamento de Horário");

        ClassPathResource resource = new ClassPathResource("messages/alocamento_horario.html");
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        String customMessage = "";
        if (mensagemCustomizada != null){
            customMessage = mensagemCustomizada;
        }

        html = html
            .replace("{{NOME_PROFESSOR}}", professor.getProfissional().getNome())
            .replace("{{NOME_DISCIPLINA}}", disciplina.getNome())
            .replace("{{MODULO}}", disciplina.getModulo().getNome())
            .replace("{{CURSO}}", disciplina.getModulo().getCurso().getNome())
            .replace("{{MENSAGEM_ADICIONAL}}", customMessage);
        
        helper.setText(html, true);
        mailSender.send(mime);
    }

    public void enviarEmailLinkResetSenha(String email, String token) throws MessagingException, IOException {
        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
        helper.setTo(email);
        helper.setSubject("Link Temporário para reset de senha");

        ClassPathResource resource = new ClassPathResource("messages/resetPassword.html");
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        String linkToken = "http://localhost:5173/novaSenha/" + token + "/" + email;

        html = html.replace("{{link}}", linkToken);

        helper.setText(html, true);
        mailSender.send(mime);
    }

    public void enviarEmailCadastro(String email, String token, String frontPath, Integer idProfessor) throws MessagingException, IOException {
        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
        helper.setTo(email);
        helper.setSubject("Link Temporário para cadastro");

        // LINK APENAS!!!!
        ClassPathResource resource = new ClassPathResource("messages/register.html");
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        String linkToken = "http://localhost:5173/" + frontPath + idProfessor + "/" + token;

        html = html.replace("{{link}}", linkToken);

        helper.setText(html, true);
        mailSender.send(mime);
    }

}
