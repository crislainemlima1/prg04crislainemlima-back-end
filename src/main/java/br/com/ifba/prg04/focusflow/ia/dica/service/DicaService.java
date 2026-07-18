package br.com.ifba.prg04.focusflow.ia.dica.service;

import br.com.ifba.prg04.focusflow.exception.ResourceNotFoundException;
import br.com.ifba.prg04.focusflow.ia.client.OpenAiClient;
import br.com.ifba.prg04.focusflow.ia.dica.dto.DicaResponseDTO;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.materia.service.MateriaService;
import br.com.ifba.prg04.focusflow.sessaoestudos.model.SessaoEstudo;
import br.com.ifba.prg04.focusflow.sessaoestudos.service.SessaoEstudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DicaService {

    private final SessaoEstudoService sessaoEstudoService;
    private final MateriaService materiaService;
    private final OpenAiClient openAiClient;

    private static final String PROMPT_SISTEMA = """
            Você é o assistente de estudos do FocusFlow. Antes de uma sessão Pomodoro, dê UMA dica
            curta (1 a 2 frases), em português, sobre como o usuário deve focar essa sessão para a
            matéria informada, com base no resumo de histórico de estudo fornecido (meta de horas,
            número de sessões e minutos totais estudados nessa matéria).
            Use apenas os dados fornecidos. NUNCA invente números, percentuais de erro, tópicos
            específicos (como "limites laterais") ou qualquer estatística que não esteja no resumo
            enviado — nesses casos, fale de forma geral sobre a matéria.
            Responda apenas com o texto da dica, sem formatação markdown.
            """;

    public DicaResponseDTO gerarDica(Long materiaId, Long usuarioId) {
        Materia materia = materiaService.buscarPorId(materiaId)
                .orElseThrow(() -> new ResourceNotFoundException("Matéria não encontrada com id: " + materiaId));

        if (!materia.getUsuario().getId().equals(usuarioId)) {
            throw new AccessDeniedException("Você não é o dono desta matéria");
        }

        List<SessaoEstudo> sessoes = sessaoEstudoService.listarPorMateria(materiaId);
        long totalMinutos = sessoes.stream().mapToLong(SessaoEstudo::getDuracaoMinutos).sum();
        int totalSessoes = sessoes.size();

        String resumo = "Matéria: %s. Meta de horas: %d. Total de sessões registradas: %d. Minutos totais estudados: %d."
                .formatted(materia.getNome(), materia.getMetaHora(), totalSessoes, totalMinutos);

        String dica = openAiClient.gerarResposta(PROMPT_SISTEMA, resumo);
        return new DicaResponseDTO(dica.trim());
    }
}
