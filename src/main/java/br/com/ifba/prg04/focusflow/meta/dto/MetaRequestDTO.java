package br.com.ifba.prg04.focusflow.meta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MetaRequestDTO {
    @NotNull(message = "As horas objetivo são obrigatórias")
    private Integer horasObjetivo;

    @NotNull(message = "A data limite é obrigatória")
    private LocalDate dataLimite;

    private Boolean concluida = false;

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "A matéria é obrigatória")
    private Long materiaId;
}
