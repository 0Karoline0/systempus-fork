package br.com.systempus.systempus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.config.JwtUtil;
import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.domain.dto.TokenDTO;
import br.com.systempus.systempus.domain.security.UserDetailsImpl;

@Service
public class AuthService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public TokenDTO getToken(Usuario usuario) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(usuario.getUserName(), usuario.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = JwtUtil.generateToken(
            usuario.getUserName(),
            userDetails.getAuthorities()
        );
        Usuario user = usuarioService.findUserByEmail(usuario.getUserName());

        // System.out.println("\n\n\n\n\n\n\n\n Usuário passado email: " + usuario.getUserName());
        // System.out.println("\nUsuário email: " + user.getUserName());
        // System.out.println("\nUsuário ID: " + user.getId());

        return new TokenDTO(token, user.getId());
    }


}
