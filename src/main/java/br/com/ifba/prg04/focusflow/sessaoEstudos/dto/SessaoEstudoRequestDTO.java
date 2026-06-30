package br.com.ifba.prg04.focusflow.sessaoEstudos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SessaoEstudoRequestDTO {

    @NotNull(message  = "A data é obrigatória")
    private LocalDate data;

    @NotNull(message = "A duração em minutos é obrigatório")
    private Integer duracaoMinutos;

    @NotNull(message = "A matéria é obrigatória")
    private Long materiaId;
}
