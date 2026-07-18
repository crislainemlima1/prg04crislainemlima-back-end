package br.com.ifba.prg04.focusflow.sessaoestudos.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SessaoEstudoResponseDTO {


    private Long id; // Identificador único da sessão de estudo
    private LocalDate data; // Data em que a sessão aconteceu
    private Integer duracaoMinutos; // Duração da sessão em minutos
    private Long materiaId; // ID da matéria associada à sessão
    private String materiaNome; // Nome da matéria associada (para facilitar a visualização)
}
