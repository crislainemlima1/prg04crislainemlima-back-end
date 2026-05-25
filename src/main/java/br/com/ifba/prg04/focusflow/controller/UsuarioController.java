package br.com.ifba.prg04.focusflow.controller;

import br.com.ifba.prg04.focusflow.DTO.UsuarioRequestDTO;
import br.com.ifba.prg04.focusflow.DTO.UsuarioResponseDTO;
import br.com.ifba.prg04.focusflow.mapper.UsuarioMapper;
import br.com.ifba.prg04.focusflow.model.Usuario;
import br.com.ifba.prg04.focusflow.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.ifba.prg04.focusflow.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioMapper mapper;

    // GET — lista todos os usuários
    @GetMapping
    public List<UsuarioResponseDTO> listarTodos() {
        return service.listarTodos()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET — busca usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT — atualiza um usuário por ID
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {
        return service.buscarPorId(id)
                .map(usuario -> {
                    usuario.setNome(dto.getNome());
                    usuario.setEmail(dto.getEmail());
                    usuario.setSenha(dto.getSenha());
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(usuario)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // POST — cria um novo usuário
    @PostMapping
    public UsuarioResponseDTO criar(@RequestBody UsuarioRequestDTO dto) {
        return mapper.toResponseDTO(service.salvar(mapper.toEntity(dto)));
    }

    // DELETE — remove um usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}