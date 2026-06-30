package br.com.ifba.prg04.focusflow.conquista.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConquistaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataConquista;
    private Long usuarioId;
    private String usuarioNome;

}
