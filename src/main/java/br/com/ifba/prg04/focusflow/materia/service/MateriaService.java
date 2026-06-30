package br.com.ifba.prg04.focusflow.materia.service;

import br.com.ifba.prg04.focusflow.exception.ResourceNotFoundException;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.materia.repository.MateriaRepository;
import br.com.ifba.prg04.focusflow.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Materia> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public List<Materia> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Optional<Materia> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Usuario buscarUsuarioOuFalhar(Long usuarioId) {
        return usuarioService.buscarPorId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + usuarioId));

    }

    @Transactional
    public Materia salvar(Materia materia) {
        return repository.save(materia);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);

    }

}
