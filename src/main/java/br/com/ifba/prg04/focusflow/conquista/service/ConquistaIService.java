package br.com.ifba.prg04.focusflow.conquista.service;

import br.com.ifba.prg04.focusflow.conquista.model.Conquista;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface ConquistaIService {

    List<Conquista> listarTodos();

    List<Conquista> listarPorUsuario(Long usuarioId);

    Optional<Conquista> buscarPorId(Long id);

    Usuario buscarUsuarioOuFalhar(Long usuarioId);

    Conquista salvar(Conquista conquista);

    void deletar(Long id);
}
