package br.com.ifba.prg04.focusflow.materia.dto;

import lombok.Data;

@Data
public class MateriaResponseDTO {

    // dto para retorna os dados de uma materia
    private Long id;
    private String nome;
    private Integer metaHora;
    private Long usuarioId;
    private String usuarioNome;
}
