package br.com.ifba.prg04.focusflow.materia.repository;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long>{

    List<Materia> findByUsuarioId(Long usuarioId);
}
