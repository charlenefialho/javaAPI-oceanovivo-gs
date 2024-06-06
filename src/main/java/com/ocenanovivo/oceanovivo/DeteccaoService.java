package com.ocenanovivo.oceanovivo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeteccaoService {

    @Autowired
    private DeteccaoRepository deteccaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private EspecieRepository especieRepository;

    public List<DeteccaoDTO> findAll() {
        return deteccaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DeteccaoDTO> findById(Long id) {
        return deteccaoRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public DeteccaoDTO save(DeteccaoDTO deteccaoDTO) {
        Deteccao deteccao = convertToEntity(deteccaoDTO);
        Deteccao savedDeteccao = deteccaoRepository.save(deteccao);
        return convertToDTO(savedDeteccao);
    }

    @Transactional
    public void deleteById(Long id) {
        deteccaoRepository.deleteById(id);
    }

    @Transactional
    public Optional<DeteccaoDTO> update(Long id, DeteccaoDTO deteccaoDTO) {
        return deteccaoRepository.findById(id).map(existingDeteccao -> {
            updateEntityFromDTO(deteccaoDTO, existingDeteccao);
            Deteccao updatedDeteccao = deteccaoRepository.save(existingDeteccao);
            return convertToDTO(updatedDeteccao);
        });
    }

    private DeteccaoDTO convertToDTO(Deteccao deteccao) {
        DeteccaoDTO dto = new DeteccaoDTO();
        dto.setIdDeteccao(deteccao.getIdDeteccao());
        dto.setUrlImagem(deteccao.getUrlImagem());
        dto.setDataDeteccao(deteccao.getDataDeteccao());
        dto.setIdUsuario(deteccao.getUsuario().getIdUsuario());
        dto.setIdLocalizacao(deteccao.getLocalizacao().getIdLocalizacao());
        dto.setIdEspecie(deteccao.getEspecie().getIdEspecie());
        return dto;
    }

    private Deteccao convertToEntity(DeteccaoDTO dto) {
        Deteccao deteccao = new Deteccao();
        deteccao.setIdDeteccao(dto.getIdDeteccao());
        deteccao.setUrlImagem(dto.getUrlImagem());
        deteccao.setDataDeteccao(dto.getDataDeteccao());
        deteccao.setUsuario(usuarioRepository.findById(dto.getIdUsuario()).orElse(null));
        deteccao.setLocalizacao(localizacaoRepository.findById(dto.getIdLocalizacao()).orElse(null));
        deteccao.setEspecie(especieRepository.findById(dto.getIdEspecie()).orElse(null));
        return deteccao;
    }

    private void updateEntityFromDTO(DeteccaoDTO dto, Deteccao deteccao) {
        deteccao.setUrlImagem(dto.getUrlImagem());
        deteccao.setDataDeteccao(dto.getDataDeteccao());
        deteccao.setUsuario(usuarioRepository.findById(dto.getIdUsuario()).orElse(null));
        deteccao.setLocalizacao(localizacaoRepository.findById(dto.getIdLocalizacao()).orElse(null));
        deteccao.setEspecie(especieRepository.findById(dto.getIdEspecie()).orElse(null));
    }
}

