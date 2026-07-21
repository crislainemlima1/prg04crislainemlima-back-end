package br.com.ifba.prg04.focusflow.usuario.service;

import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

// Interface que define o contrato do serviço de usuário
public interface UsuarioIService {


    Page<Usuario> listarTodos(Pageable pageable);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorEmail(String email);

    Usuario salvar(Usuario usuario);

    void deletar(Long id);
}
