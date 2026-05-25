package br.com.ifba.prg04.focusflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Tratamento centralizado de exceções
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata exceções de recurso não encontrado (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("status", 404);
        erro.put("mensagem", ex.getMessage());
        erro.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Trata exceções genéricas não previstas (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("status", 500);
        erro.put("mensagem", "Erro interno no servidor");
        erro.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
