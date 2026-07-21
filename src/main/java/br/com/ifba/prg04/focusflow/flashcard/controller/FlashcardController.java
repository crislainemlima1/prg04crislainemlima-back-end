package br.com.ifba.prg04.focusflow.flashcard.controller;


import br.com.ifba.prg04.focusflow.flashcard.dto.FlashcardRequestDTO;
import br.com.ifba.prg04.focusflow.flashcard.dto.FlashcardResponseDTO;
import br.com.ifba.prg04.focusflow.flashcard.mapper.FlashcardMapper;
import br.com.ifba.prg04.focusflow.flashcard.model.Flashcard;
import br.com.ifba.prg04.focusflow.flashcard.service.FlashcardService;
import br.com.ifba.prg04.focusflow.ia.resumo.model.Resumo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flashcards")
@RequiredArgsConstructor
public class FlashcardController implements FlashcardIController{

    private final FlashcardService service;
    private final FlashcardMapper mapper;

    @GetMapping
    public List<FlashcardResponseDTO> listarTodos() {
        return service.listarTodos().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/resumo/{resumoId}")
    public List<FlashcardResponseDTO> listarPorResumo(@PathVariable Long resumoId) {
        return service.listarPorResumo(resumoId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FlashcardResponseDTO criar(@RequestBody @Valid FlashcardRequestDTO dto) {
        Resumo resumo = service.buscarResumoOuFalhar(dto.getResumoId());
        Flashcard flashcard = mapper.toEntity(dto, resumo);
        return mapper.toResponseDTO(service.salvar(flashcard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlashcardResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FlashcardRequestDTO dto) {
        return service.buscarPorId(id)
                .map(flashcard -> {
                    Resumo resumo = service.buscarResumoOuFalhar(dto.getResumoId());
                    flashcard.setPergunta(dto.getPergunta());
                    flashcard.setResposta(dto.getResposta());
                    flashcard.setResumo(resumo);
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(flashcard)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
