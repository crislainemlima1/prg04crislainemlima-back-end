package br.com.ifba.prg04.focusflow.ia.sugestao.controller;

import br.com.ifba.prg04.focusflow.ia.sugestao.dto.SugestaoResponseDTO;
import br.com.ifba.prg04.focusflow.ia.sugestao.service.SugestaoService;
import br.com.ifba.prg04.focusflow.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Caixa de sugestão de estudo do Dashboard: sempre referente ao usuário autenticado
@RestController
@RequestMapping("/ia/sugestao")
@RequiredArgsConstructor
public class SugestaoController {

    private final SugestaoService service;

    @GetMapping
    public SugestaoResponseDTO gerar() {
        return service.gerarSugestao(SecurityUtils.getUsuarioAutenticadoId());
    }
}
