package br.com.ifba.prg04.focusflow.conquista.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ConquistaRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;
    private String descricao;

    @NotNull(message = "A data da conquista é obrigatória")
    private LocalDate dataConquista;

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;
}
