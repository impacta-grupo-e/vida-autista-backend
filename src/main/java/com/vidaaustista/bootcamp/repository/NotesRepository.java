package com.vidaaustista.bootcamp.repository;

import com.vidaaustista.bootcamp.entity.JornadaEntity;
import com.vidaaustista.bootcamp.entity.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity, Integer> {

    @Query(value = "SELECT * FROM vidaautista.notes_entity where id_usuario = ? order by data_observacao desc;",
            nativeQuery = true)
    List<NotesEntity> findbyIdUsuario(Integer idUser);

    @Query(value = "SELECT * FROM vidaautista.notes_entity where id_usuario = ? and id_fase = ? order by data_observacao desc;",
            nativeQuery = true)
    List<NotesEntity> findbyIdUsuarioEFase(Integer idUser, Integer fase);
}
