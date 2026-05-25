package br.com.ifba.prg04.focusflow.DTO;

import lombok.Data;

// DTO Para recebr dados do uuario
@Data
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;
}
