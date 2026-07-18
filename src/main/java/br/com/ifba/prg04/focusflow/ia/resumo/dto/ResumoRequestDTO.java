package br.com.ifba.prg04.focusflow.ia.resumo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResumoRequestDTO {

    @NotBlank(message = "O texto é obrigatório")
    @Size(max = 8000, message = "O texto não pode ter mais de 8000 caracteres")
    private String texto;
}
