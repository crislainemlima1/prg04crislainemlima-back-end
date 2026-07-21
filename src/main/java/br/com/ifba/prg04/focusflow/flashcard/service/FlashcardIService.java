package br.com.ifba.prg04.focusflow.flashcard.service;

import br.com.ifba.prg04.focusflow.flashcard.model.Flashcard;
import br.com.ifba.prg04.focusflow.ia.resumo.model.Resumo;

import java.util.List;
import java.util.Optional;

public interface FlashcardIService {

    List<Flashcard> listarTodos();
    List<Flashcard> listarPorResumo(Long resumoId);
    Optional<Flashcard> buscarPorId(Long id);
    Resumo buscarResumoOuFalhar(Long resumoId);
    Flashcard salvar(Flashcard flashcard);
    void deletar(Long id);
}
