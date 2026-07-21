package br.com.ifba.prg04.focusflow.resumo.controller;

import br.com.ifba.prg04.focusflow.resumo.dto.ResumoRequestDTO;
import br.com.ifba.prg04.focusflow.resumo.dto.ResumoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResumoIController {

    List<ResumoResponseDTO> listarTodos();
    List<ResumoResponseDTO> listarPorUsuario(Long usuarioId);
    List<ResumoResponseDTO> listarPorMateria(Long materiaId);
    ResponseEntity<ResumoResponseDTO> buscarPorId(Long id);
    ResumoResponseDTO criar(@Valid ResumoRequestDTO dto);
    ResponseEntity<ResumoResponseDTO> atualizar(Long id, @Valid ResumoRequestDTO dto);
    ResponseEntity<Void> deletar(Long id);
}
