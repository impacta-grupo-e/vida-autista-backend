package com.vidaaustista.bootcamp.service;

import com.vidaaustista.bootcamp.entity.JornadaEntity;
import com.vidaaustista.bootcamp.entity.NotesEntity;
import com.vidaaustista.bootcamp.repository.JornadaRepository;
import com.vidaaustista.bootcamp.repository.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JornadaService {

    @Autowired
    private JornadaRepository repo;

    @Autowired
    private NotesRepository notesRepository;

    public void gravarJornada(JornadaEntity jornadaEntity){
        try {
            repo.save(jornadaEntity);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir sua observação");
        }
    }

    public List<JornadaEntity> buscarJornada(Integer id){
        try {
            List<JornadaEntity> byIdUser = repo.findByIdUser(id);
            return byIdUser;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar jornada");
        }
    }

    public void gravarObservacao(NotesEntity notesEntity){
        try {
            notesRepository.save(notesEntity);
            if (notesEntity.getFaseFinalizada()){
                Optional<JornadaEntity> jornadaAtual = repo.findByIdFase(notesEntity.getIdFase(), notesEntity.getIdUsuario());
                jornadaAtual.get().setFaseFinalizada(true);
                repo.save(jornadaAtual.get());
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir sua observação");
        }
    }

    public List<NotesEntity> recuperarTodasObervacoes(int idUser) {
        return notesRepository.findbyIdUsuario(idUser);
    }

    public List<NotesEntity> recuperarTodasObervacoes(int idUser, int idFase) {
        return notesRepository.findbyIdUsuarioEFase(idUser, idFase);
    }
}
