package br.com.ifba.prg04.focusflow.usuario.dto;

import lombok.Data;

// dto para retornar os dados do usuario sem a senha
@Data
public class UsuarioResponseDTO {

    // identificadore, nome e email do usuario sem expor a senha
    private Long id;
    private String nome;
    private String email;
}
