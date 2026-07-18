package br.com.ifba.prg04.focusflow.conquista.controller;

import br.com.ifba.prg04.focusflow.conquista.dto.ConquistaRequestDTO;
import br.com.ifba.prg04.focusflow.conquista.dto.ConquistaResponseDTO;
import br.com.ifba.prg04.focusflow.conquista.mapper.ConquistaMapper;
import br.com.ifba.prg04.focusflow.conquista.model.Conquista;
import br.com.ifba.prg04.focusflow.security.SecurityUtils;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.conquista.service.ConquistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conquistas")
@RequiredArgsConstructor
public class ConquistaController implements ConquistaIController{


    private final ConquistaService service;
    private final ConquistaMapper mapper;

    // lista as conquistas do usuário autenticado
    @GetMapping
    public List<ConquistaResponseDTO> listarTodos() {
        return service.listarPorUsuario(SecurityUtils.getUsuarioAutenticadoId())
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // lista conquistas de um usuário específico (só o próprio usuário pode consultar)
    @GetMapping("/usuario/{usuarioId}")
    public List<ConquistaResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        verificarProprioUsuario(usuarioId);
        return service.listarPorUsuario(usuarioId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // busca conquista por ID (só o dono pode ver)
    @GetMapping("/{id}")
    public ResponseEntity<ConquistaResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(conquista -> {
                    verificarDono(conquista);
                    return ResponseEntity.ok(mapper.toResponseDTO(conquista));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // cria uma nova conquista para o usuário autenticado
    // (o usuarioId do corpo é ignorado por segurança; a conquista sempre é do usuário do token)
    @PostMapping
    public ConquistaResponseDTO criar(@RequestBody @Valid ConquistaRequestDTO dto) {
        Usuario usuario = service.buscarUsuarioOuFalhar(SecurityUtils.getUsuarioAutenticadoId());
        Conquista conquista = mapper.toEntity(dto, usuario);
        return mapper.toResponseDTO(service.salvar(conquista));
    }

    // atualiza uma conquista por ID (só o dono pode editar; ownership não muda)
    @PutMapping("/{id}")
    public ResponseEntity<ConquistaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ConquistaRequestDTO dto) {
        return service.buscarPorId(id)
                .map(conquista -> {
                    verificarDono(conquista);
                    conquista.setTitulo(dto.getTitulo());
                    conquista.setDescricao(dto.getDescricao());
                    conquista.setDataConquista(dto.getDataConquista());
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(conquista)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // remove uma conquista por ID (só o dono pode deletar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Conquista conquista = service.buscarPorId(id).orElse(null);
        if (conquista != null) {
            verificarDono(conquista);
        }
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private void verificarProprioUsuario(Long usuarioId) {
        if (!SecurityUtils.getUsuarioAutenticadoId().equals(usuarioId)) {
            throw new AccessDeniedException("Você só pode acessar suas próprias conquistas");
        }
    }

    private void verificarDono(Conquista conquista) {
        if (!SecurityUtils.getUsuarioAutenticadoId().equals(conquista.getUsuario().getId())) {
            throw new AccessDeniedException("Você não é o dono desta conquista");
        }
    }
}
