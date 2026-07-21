package br.com.ifba.prg04.focusflow.resumo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResumoRequestDTO {

    @NotNull(message = "A ideia central é obrigatória")
    private String ideiaCentral;

    private String conceitosChave;
    private String conexoes;
    private String dificuldadeEstimada;

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "A matéria é obrigatória")
    private Long materiaId;
}
