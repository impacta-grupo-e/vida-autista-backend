package com.vidaaustista.bootcamp.repository;

import com.vidaaustista.bootcamp.entity.JornadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JornadaRepository extends JpaRepository<JornadaEntity, Integer> {

    @Query(value = "SELECT * FROM vidaautista.jornada_entity where id_fase = ? and id_user_fk = ?;",
            nativeQuery = true)
    Optional<JornadaEntity> findByIdFase(Integer idFase, Integer idUser);

    @Query(value = "SELECT * FROM vidaautista.jornada_entity where id_user_fk = ?;",
            nativeQuery = true)
    List<JornadaEntity> findByIdUser(Integer idUser);

}
