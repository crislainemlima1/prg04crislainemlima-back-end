package br.com.ifba.prg04.focusflow.meta.controller;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.meta.dto.MetaRequestDTO;
import br.com.ifba.prg04.focusflow.meta.dto.MetaResponseDTO;
import br.com.ifba.prg04.focusflow.meta.mapper.MetaMapper;
import br.com.ifba.prg04.focusflow.meta.model.Meta;
import br.com.ifba.prg04.focusflow.meta.service.MetaService;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metas")
@RequiredArgsConstructor
public class MetaController implements MetaIController {

    private final MetaService service;
    private final MetaMapper mapper;

    @GetMapping
    public List<MetaResponseDTO> listarTodos() {
        return service.listarTodos().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<MetaResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/materia/{materiaId}")
    public List<MetaResponseDTO> listarPorMateria(@PathVariable Long materiaId) {
        return service.listarPorMateria(materiaId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MetaResponseDTO criar(@RequestBody @Valid MetaRequestDTO dto) {
        Usuario usuario = service.buscarUsuarioOuFalhar(dto.getUsuarioId());
        Materia materia = service.buscarMateriaOuFalhar(dto.getMateriaId());
        Meta meta = mapper.toEntity(dto, usuario, materia);
        return mapper.toResponseDTO(service.salvar(meta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MetaRequestDTO dto) {
        return service.buscarPorId(id)
                .map(meta -> {
                    Usuario usuario = service.buscarUsuarioOuFalhar(dto.getUsuarioId());
                    Materia materia = service.buscarMateriaOuFalhar(dto.getMateriaId());
                    meta.setHorasObjetivo(dto.getHorasObjetivo());
                    meta.setDataLimite(dto.getDataLimite());
                    meta.setConcluida(dto.getConcluida());
                    meta.setUsuario(usuario);
                    meta.setMateria(materia);
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(meta)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}