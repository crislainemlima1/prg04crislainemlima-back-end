package br.com.ifba.prg04.focusflow.sessaoestudos.repository;

import br.com.ifba.prg04.focusflow.sessaoestudos.model.SessaoEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoEstudoRepository extends JpaRepository<SessaoEstudo, Long> {

    List<SessaoEstudo> findByMateriaIdOrderByDataDescIdDesc(Long materiaId);

    List<SessaoEstudo> findByMateriaUsuarioIdOrderByDataDescIdDesc(Long usuarioId);

}
