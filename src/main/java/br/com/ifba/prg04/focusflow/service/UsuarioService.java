package br.com.ifba.prg04.focusflow.service;

import br.com.ifba.prg04.focusflow.model.Usuario;
import br.com.ifba.prg04.focusflow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // Lista todos os usuários
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // Busca usuário por ID
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Salva um novo usuário
    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    // Deleta um usuário por ID
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}