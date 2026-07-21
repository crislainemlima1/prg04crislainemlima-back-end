package br.com.ifba.prg04.focusflow.ia.resumo.repository;


import br.com.ifba.prg04.focusflow.ia.resumo.model.Resumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumoRepository extends JpaRepository<Resumo, Long> {

}
