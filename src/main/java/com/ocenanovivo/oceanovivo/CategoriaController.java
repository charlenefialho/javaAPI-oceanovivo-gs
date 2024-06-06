package com.ocenanovivo.oceanovivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<EntityModel<CategoriaDTO>> getAllCategorias() {
        return categoriaService.findAll().stream()
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).getCategoriaById(dto.getIdCategoria())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).getAllCategorias()).withRel("categorias")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CategoriaDTO>> getCategoriaById(@PathVariable Long id) {
        Optional<CategoriaDTO> categoria = categoriaService.findById(id);
        return categoria.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).getCategoriaById(dto.getIdCategoria())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).getAllCategorias()).withRel("categorias"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<CategoriaDTO>> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO createdCategoria = categoriaService.save(categoriaDTO);
        EntityModel<CategoriaDTO> resource = EntityModel.of(createdCategoria,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).getCategoriaById(createdCategoria.getIdCategoria())).withSelfRel());
        return ResponseEntity.created(URI.create("/categorias/" + createdCategoria.getIdCategoria())).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CategoriaDTO>> updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        Optional<CategoriaDTO> updatedCategoria = categoriaService.update(id, categoriaDTO);
        return updatedCategoria.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).getCategoriaById(dto.getIdCategoria())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).getAllCategorias()).withRel("categorias"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
