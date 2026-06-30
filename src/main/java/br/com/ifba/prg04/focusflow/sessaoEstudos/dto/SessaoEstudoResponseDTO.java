package br.com.ifba.prg04.focusflow.sessaoEstudos.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SessaoEstudoResponseDTO {

    private Long id;
    private LocalDate data;
    private Integer duracaoMinutos;
    private Long materiaId;
    private String materiaNome;
}
