package br.com.ifba.prg04.focusflow.flashcard.controller;

import br.com.ifba.prg04.focusflow.flashcard.dto.FlashcardRequestDTO;
import br.com.ifba.prg04.focusflow.flashcard.dto.FlashcardResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FlashcardIController {

    List<FlashcardResponseDTO> listarTodos();
    List<FlashcardResponseDTO> listarPorResumo(Long resumoId);
    ResponseEntity<FlashcardResponseDTO> buscarPorId(Long id);
    FlashcardResponseDTO criar(@Valid FlashcardRequestDTO dto);
    ResponseEntity<FlashcardResponseDTO> atualizar(Long id, @Valid FlashcardRequestDTO dto);
    ResponseEntity<Void> deletar(Long id);
}
