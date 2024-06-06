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
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping
    public List<EntityModel<LocalizacaoDTO>> getAllLocalizacoes() {
        return localizacaoService.findAll().stream()
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).getLocalizacaoById(dto.getIdLocalizacao())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).getAllLocalizacoes()).withRel("localizacoes")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<LocalizacaoDTO>> getLocalizacaoById(@PathVariable Long id) {
        Optional<LocalizacaoDTO> localizacao = localizacaoService.findById(id);
        return localizacao.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).getLocalizacaoById(dto.getIdLocalizacao())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).getAllLocalizacoes()).withRel("localizacoes"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<LocalizacaoDTO>> createLocalizacao(@RequestBody LocalizacaoDTO localizacaoDTO) {
        LocalizacaoDTO createdLocalizacao = localizacaoService.save(localizacaoDTO);
        EntityModel<LocalizacaoDTO> resource = EntityModel.of(createdLocalizacao,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).getLocalizacaoById(createdLocalizacao.getIdLocalizacao())).withSelfRel());
        return ResponseEntity.created(URI.create("/localizacoes/" + createdLocalizacao.getIdLocalizacao())).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<LocalizacaoDTO>> updateLocalizacao(@PathVariable Long id, @RequestBody LocalizacaoDTO localizacaoDTO) {
        Optional<LocalizacaoDTO> updatedLocalizacao = localizacaoService.update(id, localizacaoDTO);
        return updatedLocalizacao.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).getLocalizacaoById(dto.getIdLocalizacao())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).getAllLocalizacoes()).withRel("localizacoes"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalizacao(@PathVariable Long id) {
        localizacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
