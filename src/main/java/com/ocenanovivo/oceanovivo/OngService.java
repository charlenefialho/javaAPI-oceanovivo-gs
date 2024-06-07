package com.ocenanovivo.oceanovivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OngService {

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private DeteccaoRepository deteccaoRepository;

    public List<OngDTO> findAll() {
        return ongRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OngDTO> findById(Long id) {
        return ongRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public OngDTO save(OngDTO ongDTO) {
        Ong ong = convertToEntity(ongDTO);
        Ong savedOng = ongRepository.save(ong);
        return convertToDTO(savedOng);
    }

    @Transactional
    public void deleteById(Long id) {
        ongRepository.deleteById(id);
    }

    @Transactional
    public Optional<OngDTO> update(Long id, OngDTO ongDTO) {
        return ongRepository.findById(id).map(existingOng -> {
            updateEntityFromDTO(ongDTO, existingOng);
            Ong updatedOng = ongRepository.save(existingOng);
            return convertToDTO(updatedOng);
        });
    }

    private OngDTO convertToDTO(Ong ong) {
        OngDTO dto = new OngDTO();
        dto.setIdOng(ong.getIdOng());
        dto.setCnpj(ong.getCnpj());
        dto.setNome(ong.getNome());
        dto.setEmail(ong.getEmail());
        dto.setTelefone(ong.getTelefone());
        
        if (ong.getDeteccoes() != null) {
            dto.setIdDeteccoes(ong.getDeteccoes().stream().map(Deteccao::getIdDeteccao).collect(Collectors.toList()));
        }

        return dto;
    }

    private Ong convertToEntity(OngDTO dto) {
        Ong ong = new Ong();
        ong.setIdOng(dto.getIdOng());
        ong.setCnpj(dto.getCnpj());
        ong.setNome(dto.getNome());
        ong.setEmail(dto.getEmail());
        ong.setTelefone(dto.getTelefone());
        
        if (dto.getIdDeteccoes() != null) {
            ong.setDeteccoes(dto.getIdDeteccoes().stream().map(id -> deteccaoRepository.findById(id).orElse(null)).collect(Collectors.toList()));
        }

        return ong;
    }

    private void updateEntityFromDTO(OngDTO dto, Ong ong) {
        ong.setCnpj(dto.getCnpj());
        ong.setNome(dto.getNome());
        ong.setEmail(dto.getEmail());
        ong.setTelefone(dto.getTelefone());
        
        if (dto.getIdDeteccoes() != null) {
            ong.setDeteccoes(dto.getIdDeteccoes().stream().map(id -> deteccaoRepository.findById(id).orElse(null)).collect(Collectors.toList()));
        }
    }
}

