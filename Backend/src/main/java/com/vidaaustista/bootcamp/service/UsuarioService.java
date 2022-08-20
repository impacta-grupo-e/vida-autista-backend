package com.vidaaustista.bootcamp.service;

import com.vidaaustista.bootcamp.Exception.NotFoundException;
import com.vidaaustista.bootcamp.entity.UsuarioEntity;
import com.vidaaustista.bootcamp.mapper.*;
import com.vidaaustista.bootcamp.model.AlteracaoDTO;
import com.vidaaustista.bootcamp.model.ConsultaDTO;
import com.vidaaustista.bootcamp.model.UsuarioDTO;
import com.vidaaustista.bootcamp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private UsuarioMapper usuarioMapper;
    private ConsultaMapper consultaMapper;
    private AlteracaoMapper alteracaoMapper;

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuario){
        usuarioMapper = new UsuarioMapperImpl();
        UsuarioEntity usuarioEntity = usuarioMapper.toEntity(usuario);
        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);
        UsuarioDTO usuarioDTO = usuarioMapper.toDto(usuarioSalvo);
        return usuarioDTO;
    }


    public ConsultaDTO findByEmail(String email) {
        UsuarioEntity allById = null;
        ConsultaDTO consultaDTO = null;
        consultaMapper = new ConsultaMapperImpl();
        try {
            allById = usuarioRepository.findByEmail(email);
            consultaDTO = consultaMapper.toDto(allById);
            log.info("Consulta realizada com sucesso", consultaDTO);
        } catch (Exception e) {
            log.error("Erro ao acessar banco de dados", e);
        }
        if (allById == null) {
            throw new NotFoundException("Pesquisa não encontrada");
        }
        return consultaDTO;
    }

    public AlteracaoDTO atualizandoCadastro(AlteracaoDTO alteracaoUsuario, String email ) {
        UsuarioEntity usuarioPorEmail = null;
        alteracaoMapper = new AlteracaoMapperImpl();
        AlteracaoDTO alteracaoDTO = null;
        try {
            // pega o usuario ja existente
            usuarioPorEmail = usuarioRepository.findByEmail(email);

            //convertendo pro DTO em questao
            {
                alteracaoDTO = alteracaoMapper.toDto(usuarioPorEmail);

                alteracaoDTO.setDataNascimento((alteracaoUsuario.getDataNascimento() == null ? alteracaoDTO.getDataNascimento() : alteracaoUsuario.getDataNascimento()));
                alteracaoDTO.setDocumentoIdentificacao((alteracaoUsuario.getDocumentoIdentificacao() == null ? alteracaoDTO.getDocumentoIdentificacao() : alteracaoUsuario.getDocumentoIdentificacao()));
                alteracaoDTO.setNome((alteracaoUsuario.getNome() == null ? alteracaoDTO.getNome() : alteracaoUsuario.getNome()));
                alteracaoDTO.setTelefone((alteracaoUsuario.getTelefone() == null ? alteracaoDTO.getTelefone() : alteracaoUsuario.getTelefone()));


                UsuarioEntity usuarioEntity = alteracaoMapper.toEntity(alteracaoDTO);

                usuarioRepository.save(usuarioEntity);

                log.info("Alteracoes realizadas com sucesso", alteracaoDTO);
            }

        } catch (Exception e) {
            log.error("Usuario nao encontrado", e);
        }
        if (usuarioPorEmail == null) {
            throw new NotFoundException("Email de cadastro informado nao e valido");
        }
        return alteracaoDTO;
    }

}
