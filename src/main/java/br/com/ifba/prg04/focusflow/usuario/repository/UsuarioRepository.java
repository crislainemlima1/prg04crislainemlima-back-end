package br.com.ifba.prg04.focusflow.usuario.repository;

import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// Interface que conecta com o banco de dados
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email); //busca usuario por email
}
