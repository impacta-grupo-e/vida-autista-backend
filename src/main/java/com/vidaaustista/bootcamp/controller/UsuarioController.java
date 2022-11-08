package com.vidaaustista.bootcamp.controller;

import com.vidaaustista.bootcamp.entity.UsuarioEntity;
import com.vidaaustista.bootcamp.model.UsuarioDTO;
import com.vidaaustista.bootcamp.security.Autenticador;
import com.vidaaustista.bootcamp.security.Token;
import com.vidaaustista.bootcamp.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/user/new")
    public ResponseEntity<UsuarioDTO> inserirNovoUsuario(@RequestBody UsuarioDTO usuario) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.cadastrarUsuario(usuario);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<Token> autentica(@RequestBody UsuarioDTO usuario) {

        List<UsuarioDTO> usuarios = usuarioService.recuperarTodos();
        boolean existe = false;

        for(UsuarioDTO c : usuarios) {
            if(usuario.getEmail().equals(c.getEmail()) && c.getSenha().equals(usuario.getSenha())) {
                usuario = c;
                existe = true;
            }
        }

        if(existe) {
            String tk = Autenticador.generateToken(usuario);
            Token token = new Token();
            token.setStrToken(tk);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/user/info")
    public ResponseEntity<UsuarioDTO> getInfo(@RequestParam String token){
        if (token != null) {
            if (Autenticador.isValid(token)) {
                System.out.println("chegou info" + token);
                return ResponseEntity.ok(Autenticador.getUser(token));
            }
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/user/alterar/{id}")
    public ResponseEntity<UsuarioEntity> atualizarDados(@PathVariable int id, @RequestBody UsuarioEntity usuario){
        usuarioService.atualizarUsuario(usuario, id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/user/alterar/{id}/senha")
    public ResponseEntity<UsuarioEntity> atualizarSenha(@PathVariable int id, @RequestBody UsuarioEntity usuario){
        return ResponseEntity.ok(usuarioService.atualizarSenha(id, usuario));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UsuarioEntity> buscarUsuarioPeloId(@PathVariable int id){
        UsuarioEntity p =usuarioService.recuperarPorId(id);
        if(p != null) {
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.notFound().build();
    }
}
