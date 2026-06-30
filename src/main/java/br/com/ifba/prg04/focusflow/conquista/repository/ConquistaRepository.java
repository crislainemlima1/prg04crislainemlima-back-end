package br.com.ifba.prg04.focusflow.conquista.repository;

import br.com.ifba.prg04.focusflow.conquista.model.Conquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConquistaRepository extends JpaRepository<Conquista, Long> {

    // Buscar todas as conquistas de um usuario especifico
    List<Conquista> findByUsuarioId(Long usuarioId);
}
