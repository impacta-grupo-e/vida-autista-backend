package com.vidaaustista.bootcamp.controller;

import com.vidaaustista.bootcamp.entity.JornadaEntity;
import com.vidaaustista.bootcamp.entity.NotesEntity;
import com.vidaaustista.bootcamp.service.JornadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

    @PostMapping("/jornada/new")
    public ResponseEntity<JornadaEntity> inserirNovaObservacao(@RequestBody JornadaEntity observacao){
        try {
            jornadaService.gravarJornada(observacao);
            return ResponseEntity.ok(observacao);

        }catch(Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/jornada/{id}")
    public ResponseEntity<List<JornadaEntity>> buscarJornadaPorId(@PathVariable int id){
        System.out.println("chegou requisiton" + id);
        return ResponseEntity.ok(jornadaService.buscarJornada(id));
    }

    @PostMapping("/notes/new")
    public ResponseEntity<NotesEntity> inserirNovaObservacao(@RequestBody NotesEntity observacao){
        try {
            jornadaService.gravarObservacao(observacao);
            return ResponseEntity.ok(observacao);

        }catch(Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/notes/all/{id}")
    public ResponseEntity<List<NotesEntity>> mostrarTodasObservacoes(@PathVariable int id){
        return ResponseEntity.ok(jornadaService.recuperarTodasObervacoes(id));
    }

    @GetMapping("/notes/all/{id_user}/{id_fase}")
    public ResponseEntity<List<NotesEntity>> mostrarTodasObservacoes(@PathVariable int id_user, @PathVariable int id_fase ){
        return ResponseEntity.ok(jornadaService.recuperarTodasObervacoes(id_user, id_fase));
    }

}
