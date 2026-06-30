package br.com.ifba.prg04.focusflow.materia.controller;

import br.com.ifba.prg04.focusflow.materia.dto.MateriaRequestDTO;
import br.com.ifba.prg04.focusflow.materia.dto.MateriaResponseDTO;
import br.com.ifba.prg04.focusflow.materia.mapper.MateriaMapper;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.materia.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService service;

    @Autowired
    private MateriaMapper mapper;

    // GET — lista todas as matérias
    @GetMapping
    public List<MateriaResponseDTO> listarTodos() {
        return service.listarTodos()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET — lista matérias de um usuário específico (precisa vir ANTES do buscarPorId)
    @GetMapping("/usuario/{usuarioId}")
    public List<MateriaResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET — busca matéria por ID
    @GetMapping("/{id}")
    public ResponseEntity<MateriaResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST — cria uma nova matéria
    @PostMapping
    public MateriaResponseDTO criar(@RequestBody @Valid MateriaRequestDTO dto) {
        Usuario usuario = service.buscarUsuarioOuFalhar(dto.getUsuarioId());
        Materia materia = mapper.toEntity(dto, usuario);
        return mapper.toResponseDTO(service.salvar(materia));
    }

    // PUT — atualiza uma matéria por ID
    @PutMapping("/{id}")
    public ResponseEntity<MateriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MateriaRequestDTO dto) {
        return service.buscarPorId(id)
                .map(materia -> {
                    Usuario usuario = service.buscarUsuarioOuFalhar(dto.getUsuarioId());
                    materia.setNome(dto.getNome());
                    materia.setMetaHora(dto.getMetaHora());
                    materia.setUsuario(usuario);
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(materia)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE — remove uma matéria por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}