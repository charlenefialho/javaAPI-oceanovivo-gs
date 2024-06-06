package com.ocenanovivo.oceanovivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SituacaoService {

    @Autowired
    private SituacaoRepository situacaoRepository;

    public List<SituacaoDTO> findAll() {
        return situacaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SituacaoDTO> findById(Long id) {
        return situacaoRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public SituacaoDTO save(SituacaoDTO situacaoDTO) {
        Situacao situacao = convertToEntity(situacaoDTO);
        Situacao savedSituacao = situacaoRepository.save(situacao);
        return convertToDTO(savedSituacao);
    }

    @Transactional
    public void deleteById(Long id) {
        situacaoRepository.deleteById(id);
    }

    @Transactional
    public Optional<SituacaoDTO> update(Long id, SituacaoDTO situacaoDTO) {
        return situacaoRepository.findById(id).map(existingSituacao -> {
            updateEntityFromDTO(situacaoDTO, existingSituacao);
            Situacao updatedSituacao = situacaoRepository.save(existingSituacao);
            return convertToDTO(updatedSituacao);
        });
    }

    private SituacaoDTO convertToDTO(Situacao situacao) {
        SituacaoDTO dto = new SituacaoDTO();
        dto.setIdSituacao(situacao.getIdSituacao());
        dto.setRiscoExtincao(situacao.isRiscoExtincao());
        dto.setInvasora(situacao.isInvasora());
        return dto;
    }

    private Situacao convertToEntity(SituacaoDTO dto) {
        Situacao situacao = new Situacao();
        situacao.setIdSituacao(dto.getIdSituacao());
        situacao.setRiscoExtincao(dto.isRiscoExtincao());
        situacao.setInvasora(dto.isInvasora());
        return situacao;
    }

    private void updateEntityFromDTO(SituacaoDTO dto, Situacao situacao) {
        situacao.setRiscoExtincao(dto.isRiscoExtincao());
        situacao.setInvasora(dto.isInvasora());
    }
}

