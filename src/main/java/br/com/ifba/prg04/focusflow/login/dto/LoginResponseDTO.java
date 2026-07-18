package br.com.ifba.prg04.focusflow.login.dto;

import br.com.ifba.prg04.focusflow.usuario.dto.UsuarioResponseDTO;

// Resposta do login: token JWT (para o front enviar em "Authorization: Bearer <token>"
// nas próximas requisições) + os dados do usuário autenticado.
public record LoginResponseDTO(String token, UsuarioResponseDTO usuario) {
}
