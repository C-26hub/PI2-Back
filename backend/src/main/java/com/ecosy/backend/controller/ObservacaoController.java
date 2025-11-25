package com.ecosy.backend.controller;

import com.ecosy.backend.model.ObservacaoCampo;
import com.ecosy.backend.repository.ObservacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observacoes")
@CrossOrigin(origins = "*")
public class ObservacaoController {

    @Autowired
    private ObservacaoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ObservacaoCampo criar(@RequestBody ObservacaoCampo observacao) {
        return repository.save(observacao);
    }

    @GetMapping("/beneficiario/{beneficiarioId}")
    public List<ObservacaoCampo> listarPorBeneficiario(@PathVariable Long beneficiarioId) {
        return repository.findByBeneficiarioIdOrderByDataCriacaoDesc(beneficiarioId);
    }
}