package br.com.ifba.prg04.focusflow.conquista.controller;

import br.com.ifba.prg04.focusflow.conquista.dto.ConquistaRequestDTO;
import br.com.ifba.prg04.focusflow.conquista.dto.ConquistaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConquistaIController {

    List<ConquistaResponseDTO> listarTodos();

    List<ConquistaResponseDTO> listarPorUsuario(Long usuarioId);

    ResponseEntity<ConquistaResponseDTO> buscarPorId(Long id);

    ConquistaResponseDTO criar(@Valid ConquistaRequestDTO dto);

    ResponseEntity<ConquistaResponseDTO> atualizar(Long id, @Valid ConquistaRequestDTO dto);

    ResponseEntity<Void> deletar(Long id);
}
