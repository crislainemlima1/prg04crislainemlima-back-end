package br.com.ifba.prg04.focusflow.ia.dica.controller;

import br.com.ifba.prg04.focusflow.ia.dica.dto.DicaResponseDTO;
import br.com.ifba.prg04.focusflow.ia.dica.service.DicaService;
import br.com.ifba.prg04.focusflow.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Dica exibida antes de iniciar uma sessão Pomodoro para uma matéria específica
@RestController
@RequestMapping("/ia/dica")
@RequiredArgsConstructor
public class DicaController {

    private final DicaService service;

    @GetMapping("/{materiaId}")
    public DicaResponseDTO gerar(@PathVariable Long materiaId) {
        return service.gerarDica(materiaId, SecurityUtils.getUsuarioAutenticadoId());
    }
}
