package com.ocenanovivo.oceanovivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public List<LocalizacaoDTO> findAll() {
        return localizacaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<LocalizacaoDTO> findById(Long id) {
        return localizacaoRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public LocalizacaoDTO save(LocalizacaoDTO localizacaoDTO) {
        Localizacao localizacao = convertToEntity(localizacaoDTO);
        Localizacao savedLocalizacao = localizacaoRepository.save(localizacao);
        return convertToDTO(savedLocalizacao);
    }

    @Transactional
    public void deleteById(Long id) {
        localizacaoRepository.deleteById(id);
    }

    @Transactional
    public Optional<LocalizacaoDTO> update(Long id, LocalizacaoDTO localizacaoDTO) {
        return localizacaoRepository.findById(id).map(existingLocalizacao -> {
            updateEntityFromDTO(localizacaoDTO, existingLocalizacao);
            Localizacao updatedLocalizacao = localizacaoRepository.save(existingLocalizacao);
            return convertToDTO(updatedLocalizacao);
        });
    }

    private LocalizacaoDTO convertToDTO(Localizacao localizacao) {
        LocalizacaoDTO dto = new LocalizacaoDTO();
        dto.setIdLocalizacao(localizacao.getIdLocalizacao());
        dto.setLatitude(localizacao.getLatitude());
        dto.setLongitude(localizacao.getLongitude());
        dto.setCidade(localizacao.getCidade());
        dto.setEstado(localizacao.getEstado());
        dto.setPais(localizacao.getPais());
        return dto;
    }

    private Localizacao convertToEntity(LocalizacaoDTO dto) {
        Localizacao localizacao = new Localizacao();
        localizacao.setIdLocalizacao(dto.getIdLocalizacao());
        localizacao.setLatitude(dto.getLatitude());
        localizacao.setLongitude(dto.getLongitude());
        localizacao.setCidade(dto.getCidade());
        localizacao.setEstado(dto.getEstado());
        localizacao.setPais(dto.getPais());
        return localizacao;
    }

    private void updateEntityFromDTO(LocalizacaoDTO dto, Localizacao localizacao) {
        localizacao.setLatitude(dto.getLatitude());
        localizacao.setLongitude(dto.getLongitude());
        localizacao.setCidade(dto.getCidade());
        localizacao.setEstado(dto.getEstado());
        localizacao.setPais(dto.getPais());
    }
}

