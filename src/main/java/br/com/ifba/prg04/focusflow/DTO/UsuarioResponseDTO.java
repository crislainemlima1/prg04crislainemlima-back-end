package br.com.ifba.prg04.focusflow.DTO;

import lombok.Data;

// dto para retornar os dados do usuario sem a senha
@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
}
