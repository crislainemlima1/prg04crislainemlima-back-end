package br.com.ifba.prg04.focusflow.materia.service;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface MateriaIService {

    List<Materia> listarTodos();

    List<Materia> listarPorUsuario(Long usuarioId);

    Optional<Materia> buscarPorId(Long id);

    Usuario buscarUsuarioOuFalhar(Long usuarioId);

    Materia salvar(Materia materia);

    void deletar(Long id);
}
