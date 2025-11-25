package com.ecosy.backend.controller;

import com.ecosy.backend.exception.ResourceNotFoundException;
import com.ecosy.backend.model.Lote;
import com.ecosy.backend.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@CrossOrigin(origins = "*")
public class LoteController {

    @Autowired
    private LoteRepository repository;

    @GetMapping
    public List<Lote> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lote> buscarPorId(@PathVariable Long id) {
        Lote lote = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote não encontrado com ID: " + id));

        return ResponseEntity.ok(lote);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lote criar(@RequestBody Lote lote) {
        return repository.save(lote);
    }
}