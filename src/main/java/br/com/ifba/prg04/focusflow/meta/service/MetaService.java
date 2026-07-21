package br.com.ifba.prg04.focusflow.meta.service;

import br.com.ifba.prg04.focusflow.exception.ResourceNotFoundException;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.materia.service.MateriaService;
import br.com.ifba.prg04.focusflow.meta.mapper.MetaIService;
import br.com.ifba.prg04.focusflow.meta.model.Meta;
import br.com.ifba.prg04.focusflow.meta.repository.MetaRepository;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.usuario.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetaService implements MetaIService {

    private final MetaRepository repository;
    private final UsuarioService usuarioService;
    private final MateriaService materiaService;

    @Transactional
    public List<Meta> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public List<Meta> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public List<Meta> listarPorMateria(Long materiaId) {
        return repository.findByMateriaId(materiaId);
    }

    public Optional<Meta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Usuario buscarUsuarioOuFalhar(Long usuarioId) {
        return usuarioService.buscarPorId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + usuarioId));
    }

    public Materia buscarMateriaOuFalhar(Long materiaId) {
        return materiaService.buscarPorId(materiaId)
                .orElseThrow(() -> new ResourceNotFoundException("Matéria não encontrada com id: " + materiaId));
    }

    @Transactional
    public Meta salvar(Meta meta) {
        return repository.save(meta);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
