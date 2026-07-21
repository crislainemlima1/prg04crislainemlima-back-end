package br.com.ifba.prg04.focusflow.meta.controller;

import br.com.ifba.prg04.focusflow.meta.dto.MetaRequestDTO;
import br.com.ifba.prg04.focusflow.meta.dto.MetaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MetaIController {

    List<MetaResponseDTO> listarTodos();

    List<MetaResponseDTO> listarPorUsuario(Long usuarioId);

    List<MetaResponseDTO> listarPorMateria(Long materiaId);

    ResponseEntity<MetaResponseDTO> buscarPorId(Long id);

    MetaResponseDTO criar(@Valid MetaRequestDTO dto);

    ResponseEntity<MetaResponseDTO> atualizar(Long id, @Valid MetaRequestDTO dto);

    ResponseEntity<Void> deletar(Long id);
}
