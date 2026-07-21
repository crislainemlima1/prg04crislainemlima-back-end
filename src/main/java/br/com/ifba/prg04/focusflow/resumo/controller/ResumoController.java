package br.com.ifba.prg04.focusflow.resumo.controller;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.resumo.dto.ResumoRequestDTO;
import br.com.ifba.prg04.focusflow.resumo.dto.ResumoResponseDTO;
import br.com.ifba.prg04.focusflow.resumo.mapper.ResumoMapper;
import br.com.ifba.prg04.focusflow.resumo.model.Resumo;
import br.com.ifba.prg04.focusflow.resumo.service.ResumoService;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resumos")
@RequiredArgsConstructor
public class ResumoController implements ResumoIController {

    private final ResumoService service;
    private final ResumoMapper mapper;

    @GetMapping
    public List<ResumoResponseDTO> listarTodos() {
        return service.listarTodos().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ResumoResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/materia/{materiaId}")
    public List<ResumoResponseDTO> listarPorMateria(@PathVariable Long materiaId) {
        return service.listarPorMateria(materiaId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumoResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResumoResponseDTO criar(@RequestBody @Valid ResumoRequestDTO dto) {
        Usuario usuario = service.buscarUsuarioOuFalhar(dto.getUsuarioId());
        Materia materia = service.buscarMateriaOuFalhar(dto.getMateriaId());
        Resumo resumo = mapper.toEntity(dto, usuario, materia);
        return mapper.toResponseDTO(service.salvar(resumo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResumoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ResumoRequestDTO dto) {
        return service.buscarPorId(id)
                .map(resumo -> {
                    Usuario usuario = service.buscarUsuarioOuFalhar(dto.getUsuarioId());
                    Materia materia = service.buscarMateriaOuFalhar(dto.getMateriaId());
                    resumo.setIdeiaCentral(dto.getIdeiaCentral());
                    resumo.setConceitosChave(dto.getConceitosChave());
                    resumo.setConexoes(dto.getConexoes());
                    resumo.setDificuldadeEstimada(dto.getDificuldadeEstimada());
                    resumo.setUsuario(usuario);
                    resumo.setMateria(materia);
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(resumo)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}