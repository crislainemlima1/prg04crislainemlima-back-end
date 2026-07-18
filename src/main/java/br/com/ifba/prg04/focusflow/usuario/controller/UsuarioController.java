package br.com.ifba.prg04.focusflow.usuario.controller;

import br.com.ifba.prg04.focusflow.security.SecurityUtils;
import br.com.ifba.prg04.focusflow.usuario.dto.UsuarioRequestDTO;
import br.com.ifba.prg04.focusflow.usuario.dto.UsuarioResponseDTO;
import br.com.ifba.prg04.focusflow.usuario.mapper.UsuarioMapper;
import br.com.ifba.prg04.focusflow.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements UsuarioIController{


    private final UsuarioService service;
    private final UsuarioMapper mapper;

    // GET — lista todos os usuários (endpoint autenticado; sem papel de admin ainda,
    // então qualquer usuário logado consegue ver a lista básica — sem senha)
    @GetMapping
    public Page<UsuarioResponseDTO> listarTodos(Pageable pageable) {
        return service.listarTodos(pageable)
                .map(mapper::toResponseDTO);
    }

    // GET — busca usuário por ID (só o próprio usuário pode ver seus dados)
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        verificarProprioUsuario(id);
        return service.buscarPorId(id)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT — atualiza um usuário por ID (só o próprio usuário pode se editar)
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto) {
        verificarProprioUsuario(id);
        return service.buscarPorId(id)
                .map(usuario -> {
                    usuario.setNome(dto.getNome());
                    usuario.setEmail(dto.getEmail());
                    usuario.setSenha(dto.getSenha());
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(usuario)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // POST — cria um novo usuário (cadastro; endpoint público, ver SecurityConfig)
    @PostMapping
    public UsuarioResponseDTO criar(@RequestBody @Valid UsuarioRequestDTO dto) {
        return mapper.toResponseDTO(service.salvar(mapper.toEntity(dto)));
    }

    // DELETE — remove um usuário por ID (só o próprio usuário pode se deletar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        verificarProprioUsuario(id);
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Garante que o usuário autenticado só acesse/altere os próprios dados
    private void verificarProprioUsuario(Long id) {
        if (!SecurityUtils.getUsuarioAutenticadoId().equals(id)) {
            throw new AccessDeniedException("Você só pode acessar seus próprios dados");
        }
    }
}