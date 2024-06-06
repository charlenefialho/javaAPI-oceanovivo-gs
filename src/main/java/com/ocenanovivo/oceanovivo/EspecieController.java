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
@RequestMapping("/especies")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping
    public List<EntityModel<EspecieDTO>> getAllEspecies() {
        return especieService.findAll().stream()
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspecieController.class).getEspecieById(dto.getIdEspecie())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspecieController.class).getAllEspecies()).withRel("especies")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EspecieDTO>> getEspecieById(@PathVariable Long id) {
        Optional<EspecieDTO> especie = especieService.findById(id);
        return especie.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspecieController.class).getEspecieById(dto.getIdEspecie())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspecieController.class).getAllEspecies()).withRel("especies"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<EspecieDTO>> createEspecie(@RequestBody EspecieDTO especieDTO) {
        EspecieDTO createdEspecie = especieService.save(especieDTO);
        EntityModel<EspecieDTO> resource = EntityModel.of(createdEspecie,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspecieController.class).getEspecieById(createdEspecie.getIdEspecie())).withSelfRel());
        return ResponseEntity.created(URI.create("/especies/" + createdEspecie.getIdEspecie())).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EspecieDTO>> updateEspecie(@PathVariable Long id, @RequestBody EspecieDTO especieDTO) {
        Optional<EspecieDTO> updatedEspecie = especieService.update(id, especieDTO);
        return updatedEspecie.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspecieController.class).getEspecieById(dto.getIdEspecie())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspecieController.class).getAllEspecies()).withRel("especies"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecie(@PathVariable Long id) {
        especieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
