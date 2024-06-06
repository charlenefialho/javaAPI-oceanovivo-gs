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
@RequestMapping("/deteccoes")
public class DeteccaoController {

    @Autowired
    private DeteccaoService deteccaoService;

    @GetMapping
    public List<EntityModel<DeteccaoDTO>> getAllDeteccoes() {
        return deteccaoService.findAll().stream()
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeteccaoController.class).getDeteccaoById(dto.getIdDeteccao())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeteccaoController.class).getAllDeteccoes()).withRel("deteccoes")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<DeteccaoDTO>> getDeteccaoById(@PathVariable Long id) {
        Optional<DeteccaoDTO> deteccao = deteccaoService.findById(id);
        return deteccao.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeteccaoController.class).getDeteccaoById(dto.getIdDeteccao())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeteccaoController.class).getAllDeteccoes()).withRel("deteccoes"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<DeteccaoDTO>> createDeteccao(@RequestBody DeteccaoDTO deteccaoDTO) {
        DeteccaoDTO createdDeteccao = deteccaoService.save(deteccaoDTO);
        EntityModel<DeteccaoDTO> resource = EntityModel.of(createdDeteccao,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeteccaoController.class).getDeteccaoById(createdDeteccao.getIdDeteccao())).withSelfRel());
        return ResponseEntity.created(URI.create("/deteccoes/" + createdDeteccao.getIdDeteccao())).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<DeteccaoDTO>> updateDeteccao(@PathVariable Long id, @RequestBody DeteccaoDTO deteccaoDTO) {
        Optional<DeteccaoDTO> updatedDeteccao = deteccaoService.update(id, deteccaoDTO);
        return updatedDeteccao.map(dto -> ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeteccaoController.class).getDeteccaoById(dto.getIdDeteccao())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeteccaoController.class).getAllDeteccoes()).withRel("deteccoes"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeteccao(@PathVariable Long id) {
        deteccaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

