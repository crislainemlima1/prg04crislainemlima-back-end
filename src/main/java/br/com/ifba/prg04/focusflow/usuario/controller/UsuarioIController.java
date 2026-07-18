package br.com.ifba.prg04.focusflow.usuario.controller;

import br.com.ifba.prg04.focusflow.usuario.dto.UsuarioRequestDTO;
import br.com.ifba.prg04.focusflow.usuario.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UsuarioIController {

    Page<UsuarioResponseDTO> listarTodos(Pageable pageable);

    ResponseEntity<UsuarioResponseDTO> buscarPorId(Long id);

    UsuarioResponseDTO criar(@Valid UsuarioRequestDTO dto);

    ResponseEntity<UsuarioResponseDTO> atualizar(Long id, @Valid UsuarioRequestDTO dto);

    ResponseEntity<Void> deletar(Long id);
}
