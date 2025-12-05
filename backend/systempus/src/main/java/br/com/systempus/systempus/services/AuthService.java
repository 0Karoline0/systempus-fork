package br.com.systempus.systempus.services;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.domain.dto.TokenDTO;

@Service
public class AuthService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    public TokenDTO getToken(Usuario usuario) throws Exception {

        Usuario u = usuarioService.getUserByUsername(usuario.getUsername());

        if (!bCryptPasswordEncoder.matches(usuario.getPassword(), u.getPassword())) {
            throw new Exception("Usuário inexistente ou senha incorreta!");
        }

        var now = Instant.now();

        var scope = u.getProfissional().getRoles()
            .stream().map(r -> r.getTipoProfissional().name())
            .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
            .issuer("systempus_backend")
            .subject(u.getProfissional().getId().toString())
            .issuedAt(now)
            .expiresAt(now.plusSeconds(30 * 120))
            .claim("scope", scope)
            .build();
        
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new TokenDTO(jwtValue, u.getId());
    }


}
