package com.vidaaustista.bootcamp.mapper;

import com.vidaaustista.bootcamp.entity.UsuarioEntity;
import com.vidaaustista.bootcamp.model.ConsultaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface ConsultaMapper {


    @Mapping(source = "email", target = "email")
    @Mapping(source = "dataNascimento", target = "dataNascimento")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "flagProfissionalSaude", target = "flagProfissionalSaude")
    @Mapping(source = "documentoIdentificacao", target = "documentoIdentificacao")

    UsuarioEntity toEntity(ConsultaDTO consultaDTO);

    ConsultaDTO toDto(UsuarioEntity usuario);

}
