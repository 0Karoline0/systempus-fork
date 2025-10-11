// package br.com.systempus.systempus.controller;

// import java.io.IOException;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import br.com.systempus.systempus.services.PasswordResetTokenService;
// import jakarta.mail.MessagingException;

// @RestController
// @RequestMapping(value = "/api/v1/password")
// public class PasswordResetTokenController {

//     @Autowired
//     private PasswordResetTokenService service;

//     @PostMapping("/reset")
//     public ResponseEntity<Void> requestReset(@RequestBody Map<String, String> email) throws MessagingException, IOException {
//         service.solicitarReset(email.get("email"));
//         return ResponseEntity.ok().build();
//     }
// }
