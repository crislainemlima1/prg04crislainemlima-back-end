package br.com.ifba.prg04.focusflow.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// DTO Para recebr dados do uuario
@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório") // valida que não pode ser vazio
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório") // valida o formato de email
    @Email(message = "email invalido")
    private String email;


    @NotBlank(message = "A senha é obrigatÓria")
    @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres")
    private String senha;
}
