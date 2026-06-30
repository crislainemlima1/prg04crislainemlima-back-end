package br.com.ifba.prg04.focusflow.sessaoEstudos.repository;

import br.com.ifba.prg04.focusflow.sessaoEstudos.model.SessaoEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoEstudoRepository extends JpaRepository<SessaoEstudo, Long> {

    List<SessaoEstudo> findByMateriaId(Long materiaId);
}
