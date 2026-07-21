package br.com.ifba.prg04.focusflow.flashcard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FlashcardRequestDTO {

    @NotBlank(message = "A pergunta é obrigatória")
    private String pergunta;

    @NotBlank(message = "A resposta é obrigatória")
    private String resposta;

    @NotNull(message = "O resumo é obrigatório")
    private Long resumoId;
}
