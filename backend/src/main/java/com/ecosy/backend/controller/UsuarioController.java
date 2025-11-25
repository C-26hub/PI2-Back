package com.ecosy.backend.controller;

import com.ecosy.backend.dto.LoginDTO;
import com.ecosy.backend.exception.ResourceNotFoundException;
import com.ecosy.backend.model.Usuario;
import com.ecosy.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Usuario> listar(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criar(@RequestBody Usuario usuario) {
        String senhaPlana = usuario.getSenha();
        String senhaCriptografada = passwordEncoder.encode(senhaPlana);
        usuario.setSenha(senhaCriptografada);

        return repository.save(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginData) {
        Usuario usuario = repository.findByEmail(loginData.email);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email não encontrado");
        }

        if (passwordEncoder.matches(loginData.senha, usuario.getSenha())) {
            return ResponseEntity.ok("Login realizado com sucesso! (Acesso: " + usuario.getNivelAcesso() + ")");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        return ResponseEntity.ok(usuario);
    }
}
