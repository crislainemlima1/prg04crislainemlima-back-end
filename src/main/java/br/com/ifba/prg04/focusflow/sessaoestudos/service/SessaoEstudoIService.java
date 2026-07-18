package br.com.ifba.prg04.focusflow.sessaoestudos.service;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.sessaoestudos.model.SessaoEstudo;

import java.util.List;
import java.util.Optional;

public interface SessaoEstudoIService {

    List<SessaoEstudo> listarTodos();

    List<SessaoEstudo> listarPorMateria(Long materiaId);

    Optional<SessaoEstudo> buscarPorId(Long id);

    Materia buscarMateriaOuFalhar(Long materiaId);

    SessaoEstudo salvar(SessaoEstudo sessao);

    void deletar(Long id);
}
