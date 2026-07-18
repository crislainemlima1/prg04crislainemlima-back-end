package br.com.ifba.prg04.focusflow.ia.resumo.controller;

import br.com.ifba.prg04.focusflow.ia.resumo.dto.ResumoRequestDTO;
import br.com.ifba.prg04.focusflow.ia.resumo.dto.ResumoResponseDTO;
import br.com.ifba.prg04.focusflow.ia.resumo.service.ResumoService;
import br.com.ifba.prg04.focusflow.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Tela "Resumo com IA": o usuário cola um texto e recebe ideia central,
// conceitos-chave, conexões com o que já estuda, dificuldade estimada e um flashcard.
@RestController
@RequestMapping("/ia/resumo")
@RequiredArgsConstructor
public class ResumoController {

    private final ResumoService service;

    @PostMapping
    public ResumoResponseDTO gerar(@RequestBody @Valid ResumoRequestDTO dto) {
        return service.gerarResumo(dto.getTexto(), SecurityUtils.getUsuarioAutenticadoId());
    }
}
