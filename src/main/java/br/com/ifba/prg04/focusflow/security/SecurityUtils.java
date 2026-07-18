package br.com.ifba.prg04.focusflow.security;

import org.springframework.security.core.context.SecurityContextHolder;

// Utilitário para acessar o usuário autenticado (extraído do JWT) a partir de qualquer
// controller/service, sem precisar injetar HttpServletRequest em todo lugar.
public class SecurityUtils {

    private SecurityUtils() {
    }

    public static AuthenticatedUser getUsuarioAutenticado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AuthenticatedUser usuario) {
            return usuario;
        }
        throw new IllegalStateException("Usuário não autenticado");
    }

    public static Long getUsuarioAutenticadoId() {
        return getUsuarioAutenticado().id();
    }
}
