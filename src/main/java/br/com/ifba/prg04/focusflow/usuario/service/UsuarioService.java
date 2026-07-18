package br.com.ifba.prg04.focusflow.usuario.service;

import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor // Lombok gera o construtor com os campos final automaticame
public class UsuarioService implements UsuarioIService{

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    // Lista todos os usuários
    public Page<Usuario> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // Busca usuário por ID
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Busca usuário por e-mail (útil para login)
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    // Salva um novo usuário com senha criptografada
    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    // Deleta um usuário por ID
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}