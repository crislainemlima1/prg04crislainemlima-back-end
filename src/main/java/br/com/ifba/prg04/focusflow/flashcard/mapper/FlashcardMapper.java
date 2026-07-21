package br.com.ifba.prg04.focusflow.flashcard.mapper;

import br.com.ifba.prg04.focusflow.flashcard.dto.FlashcardRequestDTO;
import br.com.ifba.prg04.focusflow.flashcard.dto.FlashcardResponseDTO;
import br.com.ifba.prg04.focusflow.flashcard.model.Flashcard;

import br.com.ifba.prg04.focusflow.ia.resumo.model.Resumo;
import org.springframework.stereotype.Component;

@Component
public class FlashcardMapper {

    public Flashcard toEntity(FlashcardRequestDTO dto, Resumo resumo) {
        Flashcard flashcard = new Flashcard();
        flashcard.setPergunta(dto.getPergunta());
        flashcard.setResposta(dto.getResposta());
        flashcard.setResumo(resumo);
        return flashcard;
    }

    public FlashcardResponseDTO toResponseDTO(Flashcard flashcard) {
        FlashcardResponseDTO dto = new FlashcardResponseDTO();
        dto.setId(flashcard.getId());
        dto.setPergunta(flashcard.getPergunta());
        dto.setResposta(flashcard.getResposta());
        dto.setResumoId(flashcard.getResumo().getId());
        dto.setResumoIdeiaCentral(flashcard.getResumo().getIdeiaCentral());
        return dto;
    }
}
