package br.com.ifba.prg04.focusflow.repository;

import br.com.ifba.prg04.focusflow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Interface que conecta com o banco de dados
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
