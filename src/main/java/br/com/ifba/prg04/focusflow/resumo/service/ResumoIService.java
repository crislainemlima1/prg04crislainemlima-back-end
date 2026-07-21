package br.com.ifba.prg04.focusflow.resumo.service;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.resumo.model.Resumo;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface ResumoIService {

    List<Resumo> listarTodos();
    List<Resumo> listarPorUsuario(Long usuarioId);
    List<Resumo> listarPorMateria(Long materiaId);
    Optional<Resumo> buscarPorId(Long id);
    Usuario buscarUsuarioOuFalhar(Long usuarioId);
    Materia buscarMateriaOuFalhar(Long materiaId);
    Resumo salvar(Resumo resumo);
    void deletar(Long id);
}
