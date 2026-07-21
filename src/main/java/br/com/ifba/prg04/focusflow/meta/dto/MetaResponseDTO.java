package br.com.ifba.prg04.focusflow.meta.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MetaResponseDTO {

    private Long id;
    private Integer horasObjetivo;
    private LocalDate dataLimite;
    private Boolean concluida;
    private Long usuarioId;
    private String usuarioNome;
    private Long materiaId;
    private String materiaNome;
}
