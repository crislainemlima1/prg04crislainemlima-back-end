package br.com.ifba.prg04.focusflow.meta.mapper;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.meta.model.Meta;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface MetaIService {
    List<Meta> listarTodos();

    List<Meta> listarPorUsuario(Long usuarioId);

    List<Meta> listarPorMateria(Long materiaId);

    Optional<Meta> buscarPorId(Long id);

    Usuario buscarUsuarioOuFalhar(Long usuarioId);

    Materia buscarMateriaOuFalhar(Long materiaId);

    Meta salvar(Meta meta);

    void deletar(Long id);
}
