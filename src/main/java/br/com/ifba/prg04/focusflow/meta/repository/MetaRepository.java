package br.com.ifba.prg04.focusflow.meta.repository;

import br.com.ifba.prg04.focusflow.meta.model.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {

    // Busca todas as metas de um usuário específico
    List<Meta> findByUsuarioId(Long usuarioId);

    // Busca todas as metas de uma matéria específica
    List<Meta> findByMateriaId(Long materiaId);
}
