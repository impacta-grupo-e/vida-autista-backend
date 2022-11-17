package com.vidaaustista.bootcamp.model;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AlteracaoDTO {
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private String documentoIdentificacao;
    private Integer idUsuario;
    private String email;
    private String flagProfissionalSaude;
}
