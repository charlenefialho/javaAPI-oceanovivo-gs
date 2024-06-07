package com.ocenanovivo.oceanovivo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deteccoes")
public class DeteccaoController {

    private final DeteccaoService deteccaoService;

    @Autowired
    public DeteccaoController(DeteccaoService deteccaoService) {
        this.deteccaoService = deteccaoService;
    }

    @GetMapping
    public List<Deteccao> getAllDeteccoes() {
        return deteccaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deteccao> getDeteccaoById(@PathVariable Long id) {
        return deteccaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createDeteccao(@RequestBody DeteccaoDTO deteccaoDTO) {
        try {
            Deteccao createdDeteccao = deteccaoService.save(deteccaoDTO);
            return ResponseEntity.ok(createdDeteccao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Dados inválidos para criação de detecção.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDeteccao(@PathVariable Long id, @RequestBody DeteccaoDTO deteccaoDTO) {
        try {
            Deteccao updatedDeteccao = deteccaoService.update(id, deteccaoDTO);
            return ResponseEntity.ok(updatedDeteccao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Dados inválidos para atualização de detecção.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeteccao(@PathVariable Long id) {
        deteccaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


