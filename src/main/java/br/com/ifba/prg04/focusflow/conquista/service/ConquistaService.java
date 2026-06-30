package br.com.ifba.prg04.focusflow.conquista.service;

import br.com.ifba.prg04.focusflow.exception.ResourceNotFoundException;
import br.com.ifba.prg04.focusflow.conquista.model.Conquista;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.conquista.repository.ConquistaRepository;
import br.com.ifba.prg04.focusflow.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConquistaService {

    @Autowired
    private ConquistaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    // Lista todas as conquistas
    @Transactional
    public List<Conquista> listarTodos() {
        return repository.findAll();
    }

    // Lista conquistas de um usuário específico
    @Transactional
    public List<Conquista> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    // Busca conquista por ID
    public Optional<Conquista> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Busca o usuário ou lança exceção se não existir
    public Usuario buscarUsuarioOuFalhar(Long usuarioId) {
        return usuarioService.buscarPorId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + usuarioId));
    }

    // Salva uma nova conquista
    @Transactional
    public Conquista salvar(Conquista conquista) {
        return repository.save(conquista);
    }

    // Deleta uma conquista por ID
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}