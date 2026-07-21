package br.com.ifba.prg04.focusflow.flashcard.dto;

import lombok.Data;

@Data
public class FlashcardResponseDTO {


    private Long id;
    private String pergunta;
    private String resposta;
    private Long resumoId;
    private String resumoIdeiaCentral;
}
