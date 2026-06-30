package br.com.ifba.prg04.focusflow.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "O e-maeil é obrigatótio")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;
}
