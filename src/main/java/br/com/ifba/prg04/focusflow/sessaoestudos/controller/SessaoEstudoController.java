package br.com.ifba.prg04.focusflow.sessaoestudos.controller;

import br.com.ifba.prg04.focusflow.sessaoestudos.dto.SessaoEstudoRequestDTO;
import br.com.ifba.prg04.focusflow.sessaoestudos.dto.SessaoEstudoResponseDTO;
import br.com.ifba.prg04.focusflow.sessaoestudos.mapper.SessaoEstudoMapper;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.security.SecurityUtils;
import br.com.ifba.prg04.focusflow.sessaoestudos.model.SessaoEstudo;
import br.com.ifba.prg04.focusflow.sessaoestudos.service.SessaoEstudoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sessoes")
@RequiredArgsConstructor
public class SessaoEstudoController implements SessaoEstudoIController{


    private final SessaoEstudoService service;
    private final SessaoEstudoMapper mapper;


    // GET /sessoes > lista as sessões de estudo do usuário autenticado
    @GetMapping
    public List<SessaoEstudoResponseDTO> listarTodos() {
        return service.listarPorUsuario(SecurityUtils.getUsuarioAutenticadoId())
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET /sessoes/materia/{materiaId} → lista sessões de uma matéria específica
    // (só o dono da matéria pode consultar as sessões dela)
    @GetMapping("/materia/{materiaId}")
    public List<SessaoEstudoResponseDTO> listarPorMateria(@PathVariable Long materiaId) {
        Materia materia = service.buscarMateriaOuFalhar(materiaId);
        verificarDonoDaMateria(materia);
        return service.listarPorMateria(materiaId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET /sessoes/{id} > busca sessão de estudo por ID (só o dono da matéria pode ver)
    @GetMapping("/{id}")
    public ResponseEntity<SessaoEstudoResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(sessao -> {
                    verificarDonoDaSessao(sessao);
                    return ResponseEntity.ok(mapper.toResponseDTO(sessao));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /sessoes > cria uma nova sessão de estudo (a matéria informada precisa ser do usuário autenticado)
    @PostMapping
    public SessaoEstudoResponseDTO criar(@RequestBody @Valid SessaoEstudoRequestDTO dto) {
        Materia materia = service.buscarMateriaOuFalhar(dto.getMateriaId());
        verificarDonoDaMateria(materia);
        SessaoEstudo sessao = mapper.toEntity(dto, materia);
        return mapper.toResponseDTO(service.salvar(sessao));
    }

    // PUT /sessoes/{id} > atualiza uma sessão existente (só o dono pode editar)
    @PutMapping("/{id}")
    public ResponseEntity<SessaoEstudoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid SessaoEstudoRequestDTO dto) {
        return service.buscarPorId(id)
                .map(sessao -> {
                    verificarDonoDaSessao(sessao);
                    Materia materia = service.buscarMateriaOuFalhar(dto.getMateriaId());
                    verificarDonoDaMateria(materia);
                    sessao.setData(dto.getData());
                    sessao.setDuracaoMinutos(dto.getDuracaoMinutos());
                    sessao.setMateria(materia);
                    return ResponseEntity.ok(mapper.toResponseDTO(service.salvar(sessao)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /sessoes/{id} > remove uma sessão de estudo (só o dono pode deletar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        SessaoEstudo sessao = service.buscarPorId(id).orElse(null);
        if (sessao != null) {
            verificarDonoDaSessao(sessao);
        }
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private void verificarDonoDaMateria(Materia materia) {
        if (!SecurityUtils.getUsuarioAutenticadoId().equals(materia.getUsuario().getId())) {
            throw new AccessDeniedException("Você não é o dono desta matéria");
        }
    }

    private void verificarDonoDaSessao(SessaoEstudo sessao) {
        verificarDonoDaMateria(sessao.getMateria());
    }
}
