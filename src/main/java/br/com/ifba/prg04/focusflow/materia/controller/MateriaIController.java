package br.com.ifba.prg04.focusflow.materia.controller;

import br.com.ifba.prg04.focusflow.materia.dto.MateriaRequestDTO;
import br.com.ifba.prg04.focusflow.materia.dto.MateriaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MateriaIController {

    List<MateriaResponseDTO> listarTodos();

    List<MateriaResponseDTO> listarPorUsuario(Long usuarioId);

    ResponseEntity<MateriaResponseDTO> buscarPorId(Long id);

    MateriaResponseDTO criar(@Valid MateriaRequestDTO dto);

    ResponseEntity<MateriaResponseDTO> atualizar(Long id, @Valid MateriaRequestDTO dto);

    ResponseEntity<Void> deletar(Long id);
}
