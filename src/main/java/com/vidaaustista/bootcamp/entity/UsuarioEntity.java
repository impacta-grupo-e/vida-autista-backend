package com.vidaaustista.bootcamp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "senha")
    private String senha;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "flagProfissionalSaude")
    private String flagProfissionalSaude;

    @Column(name = "documentoIdentificacao")
    private String documentoIdentificacao;

    @Column(name="imagem", length=300)
    private String imagem;

    @OneToMany(targetEntity = JornadaEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="id_user_fk",referencedColumnName = "idUsuario")
    private List<JornadaEntity> jornadas;

}
