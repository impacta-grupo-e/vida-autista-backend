package com.vidaaustista.bootcamp.model;


import lombok.*;


@Data
@AllArgsConstructor
@Getter
@Setter
public class ConsultaDTO {

        private String nome;
        private String telefone;
        private String email;
        private String dataNascimento;
        private String flagProfissionalSaude;
        private String documentoIdentificacao;

}


