package com.vidaaustista.bootcamp.service;

import com.vidaaustista.bootcamp.entity.UsuarioEntity;
import com.vidaaustista.bootcamp.mapper.UsuarioMapper;
import com.vidaaustista.bootcamp.mapper.UsuarioMapperImpl;
import com.vidaaustista.bootcamp.model.UsuarioDTO;
import com.vidaaustista.bootcamp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private UsuarioMapper usuarioMapper;

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuario) {
        usuarioMapper = new UsuarioMapperImpl();
        UsuarioEntity usuarioEntity = usuarioMapper.toEntity(usuario);
        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);
        UsuarioDTO usuarioDTO = usuarioMapper.toDto(usuarioSalvo);
        return usuarioDTO;
    }

    public List<UsuarioDTO> recuperarTodos() {
        usuarioMapper = new UsuarioMapperImpl();
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        usuarios.forEach(usuariosEntity -> usuarioDTOList.add(usuarioMapper.toDto(usuariosEntity)));
        return usuarioDTOList;
    }

    public void atualizarUsuario(UsuarioEntity usuario, int id) {
        try {
            UsuarioEntity usuarioEntity = usuarioRepository.findById(id).get();
            usuarioEntity.setEmail(usuario.getEmail());
            usuarioEntity.setDataNascimento(usuario.getDataNascimento());
            usuarioEntity.setDocumentoIdentificacao(usuario.getDocumentoIdentificacao());
            usuarioEntity.setImagem(usuario.getImagem());
            usuarioEntity.setNome(usuario.getNome());
            usuarioEntity.setTelefone(usuario.getTelefone());
            usuarioRepository.save(usuarioEntity);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar novo usuario");
        }
    }

    public UsuarioEntity atualizarSenha(int id, UsuarioEntity usuario) {
        try {
            UsuarioEntity usuarioEntity = usuarioRepository.findById(id).get();
            usuarioEntity.setSenha(usuario.getSenha());
            return usuarioRepository.save(usuarioEntity);

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar novo usuario");
        }
    }

    public UsuarioEntity recuperarPorId(int id) {
        return usuarioRepository.findById(id).get();
    }
}
