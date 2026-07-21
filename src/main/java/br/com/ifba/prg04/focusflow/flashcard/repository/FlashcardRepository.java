package br.com.ifba.prg04.focusflow.flashcard.repository;

import br.com.ifba.prg04.focusflow.flashcard.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

    List<Flashcard> findByResumoId(Long resumoId);
}
