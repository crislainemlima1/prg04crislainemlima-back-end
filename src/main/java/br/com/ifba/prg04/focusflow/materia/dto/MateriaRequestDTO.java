package br.com.ifba.prg04.focusflow.materia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class MateriaRequestDTO {

    // dto para receber os dados da materia
    @NotBlank(message = "O nome da matéria é obrigatório")
    private String nome;

    @NotNull(message = "A meta de horas é obrigatório")
    private Integer metaHora;

    @NotNull(message = "O usuário é obrigatótio")
    private Long usuarioId;
}
