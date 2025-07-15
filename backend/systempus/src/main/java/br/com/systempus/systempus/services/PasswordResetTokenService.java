package br.com.systempus.systempus.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.PasswordResetToken;
import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.repository.PasswordResetTokenRepository;
import jakarta.mail.MessagingException;

@Service
public class PasswordResetTokenService {
    
    @Autowired
    private PasswordResetTokenRepository repositoryToken;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    public void solicitarReset(String email) throws MessagingException, IOException {
        Usuario usuario = usuarioService.findUserByEmail(email).orElseThrow( () -> new UsernameNotFoundException(email));

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUsuario(usuario);
        resetToken.setToken(token);
        resetToken.setDataExpiracao(LocalDateTime.now().plusMinutes(30).toLocalDate());
        resetToken.setUsado(false);

        repositoryToken.save(resetToken);

        //Service enviar email --> Enviar o link
        emailService.enviarEmailLinkResetSenha(email, token);
    }

    public void redefinirSenha(String token, String novaSenha) {
        PasswordResetToken resetToken = repositoryToken.findByToken(token).orElseThrow( () -> new IllegalArgumentException("Token Inválido")  );

        if (resetToken.getUsado() || resetToken.getDataExpiracao().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Token expirado ou já usado!");
        }

       Usuario usuario = resetToken.getUsuario();
       usuario.setPassword(novaSenha);
       usuarioService.resetPassword(usuario);

       resetToken.setUsado(true);
       repositoryToken.saveAndFlush(resetToken);
    }
    
}
