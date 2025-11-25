package com.ecosy.backend.controller;

import com.ecosy.backend.model.Entrega;
import com.ecosy.backend.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@CrossOrigin(origins = "*")
public class EntregaController {

    @Autowired
    private EntregaRepository repository;

    @GetMapping
    public List<Entrega> listar() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega criar(@RequestBody Entrega entrega) {
        return repository.save(entrega);
    }

    @GetMapping("/lote/{loteId}")
    public List<Entrega> listarPorLote(@PathVariable Long loteId) {
        return repository.findByLote_IdLote(loteId);
    }
}