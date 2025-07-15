package br.com.systempus.systempus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.config.JwtUtil;
import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.domain.dto.TokenDTO;
import br.com.systempus.systempus.services.PasswordResetTokenService;
import br.com.systempus.systempus.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordResetTokenService resetService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody Usuario usuario) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(usuario.getUserName(), usuario.getPassword()
            )
        );

        String token = jwtUtil.generateToken(usuario.getUserName());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> cadastrar(@RequestBody Usuario usuario) {
        usuarioService.register(usuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reset/password")
    public ResponseEntity<Void> resetarSenha(@RequestBody Map<String, String> param) {
        resetService.redefinirSenha(
            param.get("token"),
            param.get("password")
        );
        return ResponseEntity.ok().build();
    }


    
}
