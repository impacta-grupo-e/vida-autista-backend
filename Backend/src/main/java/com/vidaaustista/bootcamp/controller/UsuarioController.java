package com.vidaaustista.bootcamp.controller;

import com.vidaaustista.bootcamp.entity.UsuarioEntity;
import com.vidaaustista.bootcamp.model.AlteracaoDTO;
import com.vidaaustista.bootcamp.model.ConsultaDTO;
import com.vidaaustista.bootcamp.model.UsuarioDTO;
import com.vidaaustista.bootcamp.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/user/new")
    public ResponseEntity<String> inserirNovoUsuario(@RequestBody UsuarioDTO usuario) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.cadastrarUsuario(usuario);
            return new ResponseEntity<>("Usuário Cadastrado com Sucesso" + usuarioDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity consultaDadosNomeUsuario(@PathVariable String email) {
        try {
            ConsultaDTO consultaDTO = usuarioService.findByEmail(email);
            return ResponseEntity.ok(consultaDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/user/alteracao/{email}")
    public ResponseEntity alteraDadosUsuario(@PathVariable String email, @RequestBody AlteracaoDTO alteracao) {
        try {
            AlteracaoDTO alteracaoDTO = usuarioService.atualizandoCadastro(alteracao,email);
            return ResponseEntity.ok(alteracaoDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
