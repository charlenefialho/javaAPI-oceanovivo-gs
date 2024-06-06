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
@RequestMapping("/situacoes")
public class SituacaoController {

    @Autowired
    private SituacaoService situacaoService;

    @GetMapping
    public List<EntityModel<SituacaoDTO>> getAllSituacoes() {
        return situacaoService.findAll().stream()
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class).getSituacaoById(dto.getIdSituacao())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class).getAllSituacoes()).withRel("situacoes")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SituacaoDTO>> getSituacaoById(@PathVariable Long id) {
        Optional<SituacaoDTO> situacao = situacaoService.findById(id);
        return situacao.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class).getSituacaoById(dto.getIdSituacao())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class).getAllSituacoes()).withRel("situacoes"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<SituacaoDTO>> createSituacao(@RequestBody SituacaoDTO situacaoDTO) {
        SituacaoDTO createdSituacao = situacaoService.save(situacaoDTO);
        EntityModel<SituacaoDTO> resource = EntityModel.of(createdSituacao,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class).getSituacaoById(createdSituacao.getIdSituacao())).withSelfRel());
        return ResponseEntity.created(URI.create("/situacoes/" + createdSituacao.getIdSituacao())).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<SituacaoDTO>> updateSituacao(@PathVariable Long id, @RequestBody SituacaoDTO situacaoDTO) {
        Optional<SituacaoDTO> updatedSituacao = situacaoService.update(id, situacaoDTO);
        return updatedSituacao.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class).getSituacaoById(dto.getIdSituacao())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class).getAllSituacoes()).withRel("situacoes"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSituacao(@PathVariable Long id) {
        situacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

