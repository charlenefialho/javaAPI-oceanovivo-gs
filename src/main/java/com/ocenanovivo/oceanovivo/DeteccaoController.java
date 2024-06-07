package com.ocenanovivo.oceanovivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Deteccao> createDeteccao(@RequestBody DeteccaoDTO deteccaoDTO) {
        try {
            Deteccao createdDeteccao = deteccaoService.save(deteccaoDTO);
            return ResponseEntity.ok(createdDeteccao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deteccao> updateDeteccao(@PathVariable Long id, @RequestBody DeteccaoDTO deteccaoDTO) {
        try {
            Deteccao updatedDeteccao = deteccaoService.update(id, deteccaoDTO);
            return ResponseEntity.ok(updatedDeteccao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeteccao(@PathVariable Long id) {
        deteccaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


