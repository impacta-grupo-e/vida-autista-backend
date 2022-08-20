package com.vidaaustista.bootcamp.repository;

import com.vidaaustista.bootcamp.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {


    UsuarioEntity findByEmail(String email);
}
