package br.com.systempus.systempus.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.UserToken;
import br.com.systempus.systempus.domain.Usuario;
import jakarta.mail.MessagingException;

@Service
public class PasswordResetTokenService {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    public void solicitarReset(String email) throws MessagingException, IOException {
        Usuario usuario = usuarioService.findUserByEmail(email);
        UserToken token = userTokenService.save(usuario, LocalDateTime.now().plusMinutes(30));
        emailService.enviarEmailLinkResetSenha(email, token.getToken());
    }

    public void redefinirSenha(String token, String novaSenha) {
        UserToken resetToken = userTokenService.findByToken(token);

        if (resetToken.getUsado() || resetToken.getDataExpiracao().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Token expirado ou já usado!");
        }

       Usuario usuario = resetToken.getUsuario();
       usuario.setPassword(novaSenha);
       usuarioService.resetPassword(usuario);

       resetToken.setUsado(true);
       userTokenService.updateToken(resetToken);
    }
    
}
