package com.ocenanovivo.oceanovivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SituacaoRepository situacaoRepository;

    public List<EspecieDTO> findAll() {
        return especieRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EspecieDTO> findById(Long id) {
        return especieRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public EspecieDTO save(EspecieDTO especieDTO) {
        Especie especie = convertToEntity(especieDTO);
        Especie savedEspecie = especieRepository.save(especie);
        return convertToDTO(savedEspecie);
    }

    @Transactional
    public void deleteById(Long id) {
        especieRepository.deleteById(id);
    }

    @Transactional
    public Optional<EspecieDTO> update(Long id, EspecieDTO especieDTO) {
        return especieRepository.findById(id).map(existingEspecie -> {
            updateEntityFromDTO(especieDTO, existingEspecie);
            Especie updatedEspecie = especieRepository.save(existingEspecie);
            return convertToDTO(updatedEspecie);
        });
    }

    private EspecieDTO convertToDTO(Especie especie) {
        EspecieDTO dto = new EspecieDTO();
        dto.setIdEspecie(especie.getIdEspecie());
        dto.setNomeComum(especie.getNomeComum());
        dto.setNomeCientifico(especie.getNomeCientifico());
        dto.setDescricao(especie.getDescricao());
        dto.setIdCategoria(especie.getCategoria().getIdCategoria());
        dto.setIdSituacao(especie.getSituacao().getIdSituacao());
        return dto;
    }

    private Especie convertToEntity(EspecieDTO dto) {
        Especie especie = new Especie();
        especie.setIdEspecie(dto.getIdEspecie());
        especie.setNomeComum(dto.getNomeComum());
        especie.setNomeCientifico(dto.getNomeCientifico());
        especie.setDescricao(dto.getDescricao());
        especie.setCategoria(categoriaRepository.findById(dto.getIdCategoria()).orElse(null));
        especie.setSituacao(situacaoRepository.findById(dto.getIdSituacao()).orElse(null));
        return especie;
    }

    private void updateEntityFromDTO(EspecieDTO dto, Especie especie) {
        especie.setNomeComum(dto.getNomeComum());
        especie.setNomeCientifico(dto.getNomeCientifico());
        especie.setDescricao(dto.getDescricao());
        especie.setCategoria(categoriaRepository.findById(dto.getIdCategoria()).orElse(null));
        especie.setSituacao(situacaoRepository.findById(dto.getIdSituacao()).orElse(null));
    }
}

