// package br.com.systempus.systempus.controller;

// import java.io.IOException;
// import java.net.URI;
// import java.net.URISyntaxException;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
// import br.com.systempus.systempus.services.DisponibilidadeProfessorService;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.mail.MessagingException;

// @RestController
// @RequestMapping("api/v1/disponibilidadeProfessor/")
// @Tag(name = "DisponibilidadeProfessor")
// //@CrossOrigin(origins = ("*"), allowedHeaders = ("*"))(origins = ("*"), allowedHeaders = ("*"))
// public class DisponibilidadeProfessorController {

//     @Autowired
//     private DisponibilidadeProfessorService service;


//     // @GetMapping("{id}")
//     // public ResponseEntity<DisponibilidadeProfessor> getOne(@PathVariable Integer id){
//     //     return ResponseEntity.ok().body(service.getById(id));
//     // }

//     @GetMapping
//     public ResponseEntity<List<DisponibilidadeProfessor>> getAll(){
//         return ResponseEntity.ok().body(service.getAll());
//     }

//     // @PostMapping
//     // public ResponseEntity<DisponibilidadeProfessor> save(@RequestBody DisponibilidadeProfessor disponibilidadeProfessor, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
//     //     service.save(disponibilidadeProfessor);

//     //     StringBuffer path = new StringBuffer();

//     //     path.append(request.getRequestURI())
//     //         .append("/")
//     //         .append(disponibilidadeProfessor.getId());


//     //     URI uri = new URI(path.toString());

//     //     return ResponseEntity.created(uri).body(disponibilidadeProfessor);
//     // }

//     // @DeleteMapping("{id}")
//     // public ResponseEntity<DisponibilidadeProfessor> delete(@PathVariable Integer id){
//     //     service.deleteById(id);
//     //     return ResponseEntity.noContent().build();
//     // }

//     // @PutMapping("{id}")
//     // public ResponseEntity<DisponibilidadeProfessor> update(@RequestBody DisponibilidadeProfessor disponibilidadeProfessor, @PathVariable Integer id){
//     //     service.update(disponibilidadeProfessor, id);
//     //     return ResponseEntity.ok().body(disponibilidadeProfessor);
//     // }

//     // Não é mais necessário
//     // @PostMapping(value = "enviar/email/{idProfessor}/{idDisciplina}")
//     // public ResponseEntity<String> enviarEmailDisponibilidade(
//     //     @PathVariable Integer idProfessor,
//     //     @PathVariable Integer idDisciplina,
//     //     @RequestBody Mensagem body
//     // ) throws IOException, MessagingException, URISyntaxException{

//     //     String mensagemCustomizada = body.getMensagem();

//     //     emailService.enviarEmailAlteracaoHorario(idProfessor, idDisciplina, mensagemCustomizada);
        
//     //     StringBuffer path = new StringBuffer();

//     //     String mensagem = "Email enviado com sucesso!";

//     //     URI uri = new URI(path.toString());
        
//     //     return ResponseEntity.created(uri).body(mensagem);
//     // }

//     @PostMapping("enviar/whatsapp/{idProfessor}/{idDisciplina}")
//     private ResponseEntity<String> sendWhatsappMessage(
//         @PathVariable Integer idProfessor,
//         @PathVariable Integer idDisciplina,
//         @RequestBody String mensagemCustomizada
//     ) throws IOException, InterruptedException, URISyntaxException, MessagingException{
//         String message = service.sendWhatsappMessage(idProfessor, idDisciplina, mensagemCustomizada);

//         StringBuffer path = new StringBuffer();

//         URI uri = new URI(path.toString());

//         return ResponseEntity.created(uri).body(message);
//     }

// }
