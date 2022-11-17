package com.vidaaustista.bootcamp.mapper;

import com.vidaaustista.bootcamp.entity.UsuarioEntity;
import com.vidaaustista.bootcamp.model.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface UsuarioMapper {

    @Mapping(source = "flagProfissionalSaude", target = "flagProfissionalSaude", qualifiedByName = "trataFlagProfissionalSaude")
    @Mapping(source = "nome", target = "nome")
    UsuarioEntity toEntity(UsuarioDTO usuarioDTO);

    @Mapping(source = "flagProfissionalSaude", target = "flagProfissionalSaude", qualifiedByName = "trataFlagProfissionalSaudeEntity")
    UsuarioDTO toDto(UsuarioEntity usuario);

    @Named("trataFlagProfissionalSaude")
    default String trataFlagProfissional(Boolean flag){
        return flag ? "S" : "N";
    }

    @Named("trataFlagProfissionalSaudeEntity")
    default Boolean trataFlagProfissional(String flag){
        return flag.equalsIgnoreCase("S") ? true : false;
    }
}
