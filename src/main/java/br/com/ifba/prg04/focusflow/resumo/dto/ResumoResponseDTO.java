package br.com.ifba.prg04.focusflow.resumo.dto;

import lombok.Data;

@Data
public class ResumoResponseDTO {

    private Long id;
    private String ideiaCentral;
    private String conceitosChave;
    private String conexoes;
    private String dificuldadeEstimada;
    private Long usuarioId;
    private String usuarioNome;
    private Long materiaId;
    private String materiaNome;
}
