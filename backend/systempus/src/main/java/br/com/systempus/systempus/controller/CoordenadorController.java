package br.com.systempus.systempus.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systempus.systempus.domain.dto.CadastroDTO;
import br.com.systempus.systempus.domain.dto.CadastroProfissionalDTO;
import br.com.systempus.systempus.domain.dto.CoordenadorDTO;
import br.com.systempus.systempus.domain.dto.ProfissionalDTO;
import br.com.systempus.systempus.domain.role_object.Coordenador;
import br.com.systempus.systempus.services.CadastroService;
import br.com.systempus.systempus.services.CoordenadorService;
import br.com.systempus.systempus.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/coordenador/")
@Tag(name = "Coordenador")
public class CoordenadorController {

    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private UsuarioService usuarioService;
    
    // Pegando o coordenador pelo ID
    @GetMapping("{id}")
    @PreAuthorize("hasRole('COORDENADOR') and #id == authentication.principal.id")
    public ResponseEntity<Coordenador> getOne(@PathVariable Integer id){
        return ResponseEntity.ok().body(coordenadorService.getOne(id));
    }

    // Recolhendo todos os coordenadores
    @GetMapping
    @PreAuthorize("hasAnyRole('COORDENADOR', 'ADM')")
    public ResponseEntity<List<CoordenadorDTO>> getAll(){
        return ResponseEntity.ok().body(coordenadorService.getAll());
    }

    // Pegando o coordenador pelo ID
    @GetMapping("/{token}/{idCoordenador}")
    @PreAuthorize("hasRole('COORDENADOR') and #id == authentication.principal.id")
    public ResponseEntity<ProfissionalDTO> getCoordenadorById(@PathVariable String token, @PathVariable Integer idCoordenador){
        ProfissionalDTO coordenador = cadastroService.getCoordenadorById(token, idCoordenador);
        return ResponseEntity.ok().body(coordenador);
    }

    // Pré-cadastro do coordenador, feito pelo lado de dentro do sistema e que será responsável também por mandar o email para cadastro completo
    @PostMapping("/pre-cadastro")
    @PreAuthorize("hasAnyRole('COORDENADOR', 'ADM')")
    public ResponseEntity<Void> enviarEmailCadastro(@RequestBody CadastroProfissionalDTO cadastro) throws MessagingException, IOException {
        cadastroService.enviarEmailCadastro(cadastro, false);
        return ResponseEntity.ok().build();
    }

    // Cadastrando um coordenador pelo lado de fora do sistema, quando ele recebe o email para completar o cadastro
    @PostMapping("/cadastro")
    public ResponseEntity<Coordenador> save(@RequestBody CadastroDTO coordenador, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        Coordenador c = cadastroService.cadastroCoordenador(coordenador);

        StringBuffer path = new StringBuffer();

        path.append(request.getRequestURI())
            .append("/")
            .append(c.getId());

        URI uri = new URI(path.toString());

        return ResponseEntity.created(uri).body(c);
    }

    // TODO: Verificar se endpoint está sendo usado
    // Cadastrando um coordenador (provavelmente não está mais sendo usado)
    @PreAuthorize("hasAnyRole('COORDENADOR', 'ADM')")
    @PostMapping
    public ResponseEntity<Coordenador> save(@Valid @RequestBody Coordenador coordenador, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
        coordenadorService.save(coordenador);

        StringBuffer path = new StringBuffer();
        path.append(request.getRequestURI())
            .append("/")
            .append(coordenador.getId());

        URI uri = new URI(path.toString());
        return ResponseEntity.created(uri).body(coordenador);
    }

    // Deletando coordenador
    @PreAuthorize("hasRole('ADM')")
    @DeleteMapping("{id}")
    public ResponseEntity<Coordenador> delete(@PathVariable Integer id){
        coordenadorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Atualizando o coordenador pelo ID
    @PutMapping
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<Coordenador> update(@RequestBody Coordenador coordenador){
        coordenadorService.update(coordenador);
        return ResponseEntity.ok().body(coordenador);
    }

    // Atualizando parcialmente o coordenador pelo ID
    @PatchMapping("{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<Coordenador> updatePartial(@RequestBody Map<String, Object> mapValores, @PathVariable Integer id){
        Coordenador coordenaAtualizado = coordenadorService.updatePartial(mapValores, id);
        return ResponseEntity.ok().body(coordenaAtualizado);
    }

    // @PatchMapping("/status/{id}")
    // public ResponseEntity<Coordenador> updateStatusAtivacao(@PathVariable Integer id, @RequestBody Map<String, Integer> status){
    //     Coordenador coordenador = coordenadorService.changeCoordenadorStatusAtivacao(id, status);
    //     return ResponseEntity.ok().body(coordenador);
    // }
}