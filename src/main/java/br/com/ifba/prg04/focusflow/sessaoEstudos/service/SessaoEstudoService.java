package br.com.ifba.prg04.focusflow.sessaoEstudos.service;

import br.com.ifba.prg04.focusflow.exception.ResourceNotFoundException;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.materia.service.MateriaService;
import br.com.ifba.prg04.focusflow.sessaoEstudos.model.SessaoEstudo;
import br.com.ifba.prg04.focusflow.sessaoEstudos.repository.SessaoEstudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoEstudoService {

    @Autowired
    private SessaoEstudoRepository repository;

    @Autowired
    private MateriaService materiaService;

    @Transactional
    public List<SessaoEstudo> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public List<SessaoEstudo> listarPorMateria(Long materiaId){
        return repository.findByMateriaId(materiaId);
    }

    public Optional<SessaoEstudo> buscarPorId(Long id) {
        return repository.findById(id);
    }


    public Materia buscarMateriaOuFalhar(Long materiaId) {
        return materiaService.buscarPorId(materiaId)
                .orElseThrow(() -> new ResourceNotFoundException("Matéria não encontrada com id: " + materiaId));
    }

    // Salva uma nova sessão
    @Transactional
    public SessaoEstudo salvar(SessaoEstudo sessao) {
        return repository.save(sessao);
    }


    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
