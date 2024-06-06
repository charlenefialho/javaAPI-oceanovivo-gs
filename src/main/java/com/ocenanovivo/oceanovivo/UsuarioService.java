package com.ocenanovivo.oceanovivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(savedUsuario);
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Optional<UsuarioDTO> update(Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id).map(existingUsuario -> {
            updateEntityFromDTO(usuarioDTO, existingUsuario);
            Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
            return convertToDTO(updatedUsuario);
        });
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setGenero(usuario.getGenero());
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setGenero(dto.getGenero());
        return usuario;
    }

    private void updateEntityFromDTO(UsuarioDTO dto, Usuario usuario) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setGenero(dto.getGenero());
    }
}
