package com.vidaaustista.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JornadaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJornada;

    @Column(name = "id_fase")
    private Integer idFase;

    @Column(name = "texto_fase")
    private String textoFase;

    @Column(name = "fase_finalizada")
    private Boolean faseFinalizada;

}
