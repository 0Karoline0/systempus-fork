package br.com.systempus.systempus.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.domain.dto.PermissoesDTO;
import br.com.systempus.systempus.domain.dto.ProfissionalRoleDTO;
import br.com.systempus.systempus.domain.dto.TokenDTO;
import br.com.systempus.systempus.domain.role_object.ProfissionalRole;
import br.com.systempus.systempus.services.AuthService;
import br.com.systempus.systempus.services.PasswordResetTokenService;
import br.com.systempus.systempus.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordResetTokenService resetService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody Usuario usuario) {
        TokenDTO token = authService.getToken(usuario);
        return ResponseEntity.ok(token);
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

    @GetMapping("/permission/{userId}")
    public ResponseEntity<List<String>> getPermissoes(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(usuarioService.getRoles(userId));
    }

    // @GetMapping("/permission/{userId}")
    // public ResponseEntity<PermissoesDTO> getPermissoes(@PathVariable Integer userId) {
    //     return ResponseEntity.ok().body(usuarioService.getPermissoes(userId));
    // }


    
}
