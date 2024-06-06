package com.ocenanovivo.oceanovivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaDTO> findById(Long id) {
        return categoriaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = convertToEntity(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(savedCategoria);
    }

    @Transactional
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Transactional
    public Optional<CategoriaDTO> update(Long id, CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id).map(existingCategoria -> {
            updateEntityFromDTO(categoriaDTO, existingCategoria);
            Categoria updatedCategoria = categoriaRepository.save(existingCategoria);
            return convertToDTO(updatedCategoria);
        });
    }

    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setNome(categoria.getNome());
        dto.setHabitat(categoria.getHabitat());
        dto.setReino(categoria.getReino());
        dto.setFamilia(categoria.getFamilia());
        return dto;
    }

    private Categoria convertToEntity(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(dto.getIdCategoria());
        categoria.setNome(dto.getNome());
        categoria.setHabitat(dto.getHabitat());
        categoria.setReino(dto.getReino());
        categoria.setFamilia(dto.getFamilia());
        return categoria;
    }

    private void updateEntityFromDTO(CategoriaDTO dto, Categoria categoria) {
        categoria.setNome(dto.getNome());
        categoria.setHabitat(dto.getHabitat());
        categoria.setReino(dto.getReino());
        categoria.setFamilia(dto.getFamilia());
    }
}
