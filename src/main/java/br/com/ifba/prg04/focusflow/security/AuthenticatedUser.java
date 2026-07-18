package br.com.ifba.prg04.focusflow.security;

// Representa o usuário autenticado extraído do token JWT.
// Fica disponível como "principal" no SecurityContext durante a requisição.
public record AuthenticatedUser(Long id, String email) {
}
