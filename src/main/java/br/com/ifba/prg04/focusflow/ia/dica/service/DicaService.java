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
        Você é um tutor especialista do FocusFlow. Recebe um texto de estudo e deve produzir
        uma análise pedagógica profunda. Retorne SOMENTE um JSON válido, sem texto antes ou
        depois e sem blocos markdown, exatamente neste formato:
        {
          "ideiaCentral": "Síntese do conceito principal em UMA frase de até 25 palavras, escrita com suas próprias palavras — NUNCA copie trechos do texto original",
          "conceitosChave": [
            "Conceito 1 explicado de forma diferente do texto, mostrando o porquê ele importa",
            "Conceito 2 com uma analogia ou exemplo prático que NÃO aparece no texto",
            "Conceito 3 conectando a ideia a uma aplicação real ou consequência prática"
          ],
          "conexoes": "Explique como esse conteúdo se relaciona com as matérias que o usuário já estuda, apontando onde esse conhecimento será usado na prática. Se não houver matérias, dê uma observação pedagógica sobre pré-requisitos ou próximos passos de estudo.",
          "dificuldadeEstimada": "facil ou intermediario ou dificil",
          "flashcard": {
            "pergunta": "Uma pergunta que exige raciocínio ou aplicação — NUNCA pergunte algo cuja resposta apareça literalmente no texto. Prefira 'Por que...', 'Como...', 'O que acontece quando...'",
            "resposta": "Resposta direta, clara e completa em no máximo 2 frases, usando linguagem diferente do texto original"
          }
        }
        Regras obrigatórias:
        1. NUNCA repita frases ou expressões do texto original.
        2. A ideia central deve ser uma reinterpretação, não uma cópia.
        3. Os conceitos-chave devem trazer valor além do que está no texto (analogias, exemplos, consequências).
        4. O flashcard deve testar compreensão real, não memorização literal.
        5. Responda em português.
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
