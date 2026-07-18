package br.com.ifba.prg04.focusflow.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    // Valida que o campo não pode ser vazio ou só espaços em branco
    @NotBlank(message = "O e-maeil é obrigatótio")
    private String email;

    // Valida que o campo não pode ser vazio ou só espaços em branco
    @NotBlank(message = "A senha é obrigatória")
    private String senha;
}
