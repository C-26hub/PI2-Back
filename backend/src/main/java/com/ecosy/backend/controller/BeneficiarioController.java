package com.ecosy.backend.controller;

import com.ecosy.backend.model.Beneficiario;
import com.ecosy.backend.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
@CrossOrigin(origins = "*") // permite acesso em qaulquer lugar
public class BeneficiarioController {

    @Autowired
    private BeneficiarioRepository repository;

    @GetMapping
    public List<Beneficiario> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Beneficiario buscarPorId(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficiario criar(@RequestBody Beneficiario beneficiario) {

        System.out.println("NOME CHEGOU? " + beneficiario.getNome());
        System.out.println("CPF CHEGOU? " + beneficiario.getCpf());

        return repository.save(beneficiario);
    }
}
