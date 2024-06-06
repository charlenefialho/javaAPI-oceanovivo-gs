package com.ocenanovivo.oceanovivo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<EntityModel<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();
        return usuarios.stream()
                .map(usuario -> EntityModel.of(usuario,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(usuario.getIdUsuario())).withSelfRel()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioDTO>> getUsuarioById(@PathVariable Long id) {
        Optional<UsuarioDTO> usuarioOpt = usuarioService.findById(id);
        if (usuarioOpt.isPresent()) {
            UsuarioDTO usuario = usuarioOpt.get();
            EntityModel<UsuarioDTO> resource = EntityModel.of(usuario,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO savedUsuario = usuarioService.save(usuarioDTO);
        return EntityModel.of(savedUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(savedUsuario.getIdUsuario())).withSelfRel());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioDTO>> updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        Optional<UsuarioDTO> usuarioOpt = usuarioService.update(id, usuarioDTO);
        if (usuarioOpt.isPresent()) {
            UsuarioDTO updatedUsuario = usuarioOpt.get();
            EntityModel<UsuarioDTO> resource = EntityModel.of(updatedUsuario,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(id)).withSelfRel());
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}

