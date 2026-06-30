package br.com.ifba.prg04.focusflow.sessaoEstudos.controller;

import br.com.ifba.prg04.focusflow.sessaoEstudos.dto.SessaoEstudoRequestDTO;
import br.com.ifba.prg04.focusflow.sessaoEstudos.dto.SessaoEstudoResponseDTO;
import br.com.ifba.prg04.focusflow.sessaoEstudos.mapper.SessaoEstudoMapper;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.sessaoEstudos.model.SessaoEstudo;
import br.com.ifba.prg04.focusflow.sessaoEstudos.service.SessaoEstudoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sessoes")
public class SessaoEstudoController {

    @Autowired
    private SessaoEstudoService service;

    @Autowired
    private SessaoEstudoMapper mapper;


    @GetMapping
    public List<SessaoEstudoResponseDTO> listarTodos() {
        return service.listarTodos()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/materia/{materiaId}")
    public List<SessaoEstudoResponseDTO> listarPorMateria(@PathVariable Long materiaId) {
        return service.listarPorMateria(materiaId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<SessaoEstudoResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public SessaoEstudoResponseDTO criar(@RequestBody @Valid SessaoEstudoRequestDTO dto) {
        Materia materia = service.buscarMateriaOuFalhar(dto.getMateriaId());
        SessaoEstudo sessao = mapper.toEntity(dto, materia);
        return mapper.toResponseDTO(service.salvar(sessao));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SessaoEstudoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid SessaoEstudoRequestDTO dto) {
        return service.buscarPorId(id)
                .map(sessao -> {
                    Materia materia = service.buscarMateriaOuFalhar(dto.getMateriaId());
                    sessao.setData(dto.getData());
                    sessao.setDuracaoMinutos(dto.getDuracaoMinutos());
                    sessao.setMateria(materia);
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(sessao)));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}