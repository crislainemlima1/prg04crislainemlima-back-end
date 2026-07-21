package br.com.ifba.prg04.focusflow.flashcard.service;

import br.com.ifba.prg04.focusflow.exception.ResourceNotFoundException;
import br.com.ifba.prg04.focusflow.flashcard.model.Flashcard;
import br.com.ifba.prg04.focusflow.flashcard.repository.FlashcardRepository;

import br.com.ifba.prg04.focusflow.ia.resumo.model.Resumo;
import br.com.ifba.prg04.focusflow.ia.resumo.service.ResumoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlashcardService implements FlashcardIService{
    private final FlashcardRepository repository;
    private final ResumoService resumoService;

    @Transactional
    public List<Flashcard> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public List<Flashcard> listarPorResumo(Long resumoId) {
        return repository.findByResumoId(resumoId);
    }

    public Optional<Flashcard> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Resumo buscarResumoOuFalhar(Long resumoId) {
        return resumoService.buscarPorId(resumoId)
                .orElseThrow(() -> new ResourceNotFoundException("Resumo não encontrado com id: " + resumoId));
    }

    @Transactional
    public Flashcard salvar(Flashcard flashcard) {
        return repository.save(flashcard);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
