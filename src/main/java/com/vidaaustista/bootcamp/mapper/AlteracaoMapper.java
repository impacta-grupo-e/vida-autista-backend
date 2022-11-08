package com.vidaaustista.bootcamp.mapper;

import com.vidaaustista.bootcamp.entity.UsuarioEntity;
import com.vidaaustista.bootcamp.model.AlteracaoDTO;
import com.vidaaustista.bootcamp.model.ConsultaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AlteracaoMapper {

    @Mapping(source = "dataNascimento", target = "dataNascimento")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "documentoIdentificacao", target = "documentoIdentificacao")
    @Mapping(source = "idUsuario", target = "idUsuario")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "flagProfissionalSaude", target = "flagProfissionalSaude")

    UsuarioEntity toEntity(AlteracaoDTO alteracaoDTO);

    AlteracaoDTO toDto(UsuarioEntity usuario);

}
