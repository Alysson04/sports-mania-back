package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.Mapper.Mapper;
import com.example.demo.model.Usuario;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public Usuario createUsuario(Usuario usuario) {
        // Aqui você pode adicionar lógica de criptografia de senha se necessário
        return usuarioRepository.save(usuario);
    }


       
        public UsuarioService(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }
    
        public UsuarioDTO criarUsuario(UsuarioDTO dto) {
            Usuario usuario = modelMapper.map(dto, Usuario.class);
            Usuario usuarioSalvo = usuarioRepository.save(usuario);
            return modelMapper.map(usuarioSalvo, UsuarioDTO.class);
        }
    

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            return null;// deveria fazer tratamento de exc eçõ
        }
    }
    
    public Usuario findByUsernameAndPassword(String username, String password) {
        return usuarioRepository.findByUsernameAndPassword(username, password);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}