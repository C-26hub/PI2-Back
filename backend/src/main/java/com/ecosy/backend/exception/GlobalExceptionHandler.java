package com.ecosy.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Tratamento para "Não Encontrado" (Erro 404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND; // 404

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    // 2. Tratamento para Duplicidade ou Violação de Banco (Erro 409 ou 400)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {
        String error = "Violação de Integridade de Dados";
        HttpStatus status = HttpStatus.CONFLICT; // 409 Conflict

        String message = "Erro de banco de dados. Verifique se já não existe um registro com este CPF, Email ou Código.";

        // Tenta pegar a mensagem específica do banco se possível
        if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
            message = "Já existe um registro com este dado único (CPF, Email ou Código).";
        }

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    // 3. Tratamento Genérico (Qualquer outro erro não previsto - Erro 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> genericError(Exception e, HttpServletRequest request) {
        String error = "Erro Interno do Servidor";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                "Ocorreu um erro inesperado: " + e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}