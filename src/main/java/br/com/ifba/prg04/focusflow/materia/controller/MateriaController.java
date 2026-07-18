package br.com.ifba.prg04.focusflow.materia.controller;

import br.com.ifba.prg04.focusflow.materia.dto.MateriaRequestDTO;
import br.com.ifba.prg04.focusflow.materia.dto.MateriaResponseDTO;
import br.com.ifba.prg04.focusflow.materia.mapper.MateriaMapper;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.security.SecurityUtils;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import br.com.ifba.prg04.focusflow.materia.service.MateriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materias")
@RequiredArgsConstructor
public class MateriaController implements MateriaIController{


    private final MateriaService service;
    private final MateriaMapper mapper;

    // GET — lista as matérias do usuário autenticado
    @GetMapping
    public List<MateriaResponseDTO> listarTodos() {
        return service.listarPorUsuario(SecurityUtils.getUsuarioAutenticadoId())
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET — lista matérias de um usuário específico (só o próprio usuário pode consultar as suas)
    @GetMapping("/usuario/{usuarioId}")
    public List<MateriaResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        verificarProprioUsuario(usuarioId);
        return service.listarPorUsuario(usuarioId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET — busca matéria por ID (só o dono da matéria pode ver)
    @GetMapping("/{id}")
    public ResponseEntity<MateriaResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(materia -> {
                    verificarDono(materia);
                    return ResponseEntity.ok(mapper.toResponseDTO(materia));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // POST — cria uma nova matéria para o usuário autenticado
    // (o usuarioId do corpo é ignorado por segurança; a matéria sempre é do usuário do token)
    @PostMapping
    public MateriaResponseDTO criar(@RequestBody @Valid MateriaRequestDTO dto) {
        Usuario usuario = service.buscarUsuarioOuFalhar(SecurityUtils.getUsuarioAutenticadoId());
        Materia materia = mapper.toEntity(dto, usuario);
        return mapper.toResponseDTO(service.salvar(materia));
    }

    // PUT — atualiza uma matéria por ID (só o dono pode editar; ownership não muda)
    @PutMapping("/{id}")
    public ResponseEntity<MateriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MateriaRequestDTO dto) {
        return service.buscarPorId(id)
                .map(materia -> {
                    verificarDono(materia);
                    materia.setNome(dto.getNome());
                    materia.setMetaHora(dto.getMetaHora());
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(materia)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE — remove uma matéria por ID (só o dono pode deletar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Materia materia = service.buscarPorId(id).orElse(null);
        if (materia != null) {
            verificarDono(materia);
        }
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private void verificarProprioUsuario(Long usuarioId) {
        if (!SecurityUtils.getUsuarioAutenticadoId().equals(usuarioId)) {
            throw new AccessDeniedException("Você só pode acessar suas próprias matérias");
        }
    }

    private void verificarDono(Materia materia) {
        if (!SecurityUtils.getUsuarioAutenticadoId().equals(materia.getUsuario().getId())) {
            throw new AccessDeniedException("Você não é o dono desta matéria");
        }
    }
}
