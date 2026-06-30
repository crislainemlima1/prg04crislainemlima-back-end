package br.com.ifba.prg04.focusflow.conquista.controller;

import br.com.ifba.prg04.focusflow.conquista.dto.ConquistaRequestDTO;
import br.com.ifba.prg04.focusflow.conquista.dto.ConquistaResponseDTO;
import br.com.ifba.prg04.focusflow.conquista.mapper.ConquistaMapper;
import br.com.ifba.prg04.focusflow.conquista.model.Conquista;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.conquista.service.ConquistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conquistas")
public class ConquistaController {

    @Autowired
    private ConquistaService service;

    @Autowired
    private ConquistaMapper mapper;

    // lista todas as conquistas
    @GetMapping
    public List<ConquistaResponseDTO> listarTodos() {
        return service.listarTodos()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // lista conquistas de um usuário específico (precisa vir ANTES do buscarPorId)
    @GetMapping("/usuario/{usuarioId}")
    public List<ConquistaResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // busca conquista por ID
    @GetMapping("/{id}")
    public ResponseEntity<ConquistaResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // cria uma nova conquista
    @PostMapping
    public ConquistaResponseDTO criar(@RequestBody @Valid ConquistaRequestDTO dto) {
        Usuario usuario = service.buscarUsuarioOuFalhar(dto.getUsuarioId());
        Conquista conquista = mapper.toEntity(dto, usuario);
        return mapper.toResponseDTO(service.salvar(conquista));
    }

    // atualiza uma conquista por ID
    @PutMapping("/{id}")
    public ResponseEntity<ConquistaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ConquistaRequestDTO dto) {
        return service.buscarPorId(id)
                .map(conquista -> {
                    Usuario usuario = service.buscarUsuarioOuFalhar(dto.getUsuarioId());
                    conquista.setTitulo(dto.getTitulo());
                    conquista.setDescricao(dto.getDescricao());
                    conquista.setDataConquista(dto.getDataConquista());
                    conquista.setUsuario(usuario);
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(conquista)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //remove uma conquista por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}