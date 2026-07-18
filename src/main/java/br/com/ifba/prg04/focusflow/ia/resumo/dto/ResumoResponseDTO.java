package br.com.ifba.prg04.focusflow.ia.resumo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResumoResponseDTO {

    private String ideiaCentral;
    private List<String> conceitosChave;
    private String conexoes;
    private String dificuldadeEstimada; // "facil" | "intermediario" | "dificil"
    private FlashcardDTO flashcard;
}
