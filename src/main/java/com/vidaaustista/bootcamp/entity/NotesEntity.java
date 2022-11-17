package com.vidaaustista.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NotesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnotacao;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "data_observacao")
    private String dataObservacao;

    @Column(name = "id_fase")
    private Integer idFase;

    @Column(name = "fase_finalizada")
    private Boolean faseFinalizada;

    @Column(name = "idUsuario")
    private Integer idUsuario;

}
