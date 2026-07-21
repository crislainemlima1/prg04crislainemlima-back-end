package br.com.ifba.prg04.focusflow.ia.resumo.service;

import br.com.ifba.prg04.focusflow.ia.client.OpenAiClient;
import br.com.ifba.prg04.focusflow.ia.resumo.dto.ResumoResponseDTO;
import br.com.ifba.prg04.focusflow.ia.resumo.model.Resumo;
import br.com.ifba.prg04.focusflow.ia.resumo.repository.ResumoRepository;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.materia.service.MateriaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumoService {

    private final OpenAiClient openAiClient;
    private final MateriaService materiaService;
    private final ObjectMapper objectMapper;
    private final ResumoRepository resumoRepository;

    private static final String PROMPT_SISTEMA = """
            Você é o assistente de estudos do FocusFlow. Sua tarefa é analisar um texto de estudo
            (trecho de livro, anotação ou transcrição de aula) enviado pelo usuário e retornar
            SOMENTE um JSON válido, sem nenhum texto antes ou depois e sem blocos de código
            markdown, exatamente no formato:
            {
              "ideiaCentral": "string com o conceito mais importante do texto",
              "conceitosChave": ["conceito 1", "conceito 2"],
              "conexoes": "string curta explicando como esse conteúdo se conecta com as matérias que o usuário já estuda (ou uma observação genérica, se não houver matérias informadas)",
              "dificuldadeEstimada": "facil ou intermediario ou dificil",
              "flashcard": { "pergunta": "uma pergunta de revisão sobre o texto", "resposta": "a resposta correta" }
            }
            Responda em português. Baseie-se apenas no texto fornecido pelo usuário, sem inventar
            fatos que não estejam nele.
            """;

    public ResumoResponseDTO gerarResumo(String texto, Long usuarioId) {
        List<String> materiasDoUsuario = materiaService.listarPorUsuario(usuarioId)
                .stream()
                .map(Materia::getNome)
                .toList();

        String contexto = materiasDoUsuario.isEmpty()
                ? "O usuário ainda não cadastrou nenhuma matéria no FocusFlow."
                : "Matérias que o usuário já estuda no FocusFlow: " + String.join(", ", materiasDoUsuario) + ".";

        String promptUsuario = contexto + "\n\nTexto para analisar:\n" + texto;

        String respostaBruta = openAiClient.gerarResposta(PROMPT_SISTEMA, promptUsuario);

        try {
            return objectMapper.readValue(limparJson(respostaBruta), ResumoResponseDTO.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(
                    "Não foi possível interpretar a resposta da IA como JSON. Tente novamente.", e);
        }
    }

    public Optional<Resumo> buscarPorId(Long id) {
        return resumoRepository.findById(id);
    }
    // Precaução: caso o modelo devolva o JSON envolto em ```json ... ```, remove as cercas
    private String limparJson(String resposta) {
        String limpo = resposta.trim();
        if (limpo.startsWith("```")) {
            limpo = limpo.replaceFirst("^```(json)?", "").trim();
            if (limpo.endsWith("```")) {
                limpo = limpo.substring(0, limpo.length() - 3).trim();
            }
        }
        return limpo;
    }
}
