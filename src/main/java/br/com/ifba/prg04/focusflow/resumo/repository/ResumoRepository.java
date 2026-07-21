package br.com.ifba.prg04.focusflow.resumo.repository;

import br.com.ifba.prg04.focusflow.resumo.model.Resumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumoRepository extends JpaRepository<Resumo, Long> {

    List<Resumo> findByUsuarioId(Long usuarioId);
    List<Resumo> findByMateriaId(Long materiaId);
}
