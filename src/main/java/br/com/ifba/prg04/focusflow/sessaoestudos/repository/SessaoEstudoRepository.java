package br.com.ifba.prg04.focusflow.sessaoestudos.repository;

import br.com.ifba.prg04.focusflow.sessaoestudos.model.SessaoEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoEstudoRepository extends JpaRepository<SessaoEstudo, Long> {

    // Método customizado busca todas as sessões de estudo de uma matéria específica
    List<SessaoEstudo> findByMateriaId(Long materiaId);

    // Busca todas as sessões de estudo cujas matérias pertencem a um usuário específico
    List<SessaoEstudo> findByMateriaUsuarioId(Long usuarioId);
}
