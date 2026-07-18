package br.com.ifba.prg04.focusflow.sessaoestudos.controller;

import br.com.ifba.prg04.focusflow.sessaoestudos.dto.SessaoEstudoRequestDTO;
import br.com.ifba.prg04.focusflow.sessaoestudos.dto.SessaoEstudoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SessaoEstudoIController {

    List<SessaoEstudoResponseDTO> listarTodos();

    List<SessaoEstudoResponseDTO> listarPorMateria(Long materiaId);

    ResponseEntity<SessaoEstudoResponseDTO> buscarPorId(Long id);

    SessaoEstudoResponseDTO criar(@Valid SessaoEstudoRequestDTO dto);

    ResponseEntity<SessaoEstudoResponseDTO> atualizar(Long id, @Valid SessaoEstudoRequestDTO dto);

    ResponseEntity<Void> deletar(Long id);
}
