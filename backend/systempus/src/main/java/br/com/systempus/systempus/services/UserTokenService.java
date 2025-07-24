package br.com.systempus.systempus.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.UserToken;
import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.repository.UserTokenRepository;

@Service
public class UserTokenService {

    @Autowired
    private UserTokenRepository tokenRepository;

    public UserToken save(Usuario usuario, LocalDateTime dataExpiracao) {
        String token = UUID.randomUUID().toString();

        UserToken resetToken = new UserToken();
        resetToken.setUsuario(usuario);
        resetToken.setToken(token);
        resetToken.setDataExpiracao(dataExpiracao.toLocalDate());
        resetToken.setUsado(false);

        return tokenRepository.save(resetToken);
    }

    public UserToken findByToken(String token) {
        return tokenRepository.findByToken(token).orElseThrow( () -> new IllegalArgumentException("Token Inválido"));
    }

    public UserToken updateToken(UserToken userToken) {
        return tokenRepository.saveAndFlush(userToken);
    }

    public void validateToken(UserToken userToken) {
        isTokenExpired(userToken);
        isTokenUsed(userToken);
    }

    public void isTokenExpired(UserToken userToken) {
        if (userToken.getDataExpiracao().isEqual(LocalDateTime.now().toLocalDate()) || userToken.getDataExpiracao().isBefore(LocalDateTime.now().toLocalDate())) {
            throw new IllegalArgumentException("Token expirado!");
        }
    }

    public void isTokenUsed(UserToken userToken) {
        if (userToken.getUsado()) {
            throw new IllegalArgumentException("Token já usado!");
        }
    }

}