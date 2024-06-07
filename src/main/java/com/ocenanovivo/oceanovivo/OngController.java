package com.ocenanovivo.oceanovivo;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ongs")
@Validated
public class OngController {

    @Autowired
    private OngService ongService;

    @GetMapping
    public ResponseEntity<List<EntityModel<OngDTO>>> getAllOngs() {
        List<EntityModel<OngDTO>> ongs = ongService.findAll().stream()
                .map(ong -> EntityModel.of(ong,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OngController.class).getOngById(ong.getIdOng())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OngController.class).getAllOngs()).withRel("ongs")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ongs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OngDTO>> getOngById(@PathVariable Long id) {
        Optional<OngDTO> ong = ongService.findById(id);
        return ong.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OngController.class).getOngById(dto.getIdOng())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OngController.class).getAllOngs()).withRel("ongs"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<OngDTO>> createOng(@Valid @RequestBody OngDTO ongDTO) {
        OngDTO createdOng = ongService.save(ongDTO);
        EntityModel<OngDTO> resource = EntityModel.of(createdOng,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OngController.class).getOngById(createdOng.getIdOng())).withSelfRel());
        return ResponseEntity.created(URI.create("/ongs/" + createdOng.getIdOng())).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<OngDTO>> updateOng(@PathVariable Long id, @Valid @RequestBody OngDTO ongDTO) {
        Optional<OngDTO> updatedOng = ongService.update(id, ongDTO);
        return updatedOng.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OngController.class).getOngById(dto.getIdOng())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OngController.class).getAllOngs()).withRel("ongs"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable Long id) {
        ongService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
