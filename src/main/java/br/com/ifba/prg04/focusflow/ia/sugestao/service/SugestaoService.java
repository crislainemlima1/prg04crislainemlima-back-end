package br.com.ifba.prg04.focusflow.ia.sugestao.service;

import br.com.ifba.prg04.focusflow.ia.client.OpenAiClient;
import br.com.ifba.prg04.focusflow.ia.sugestao.dto.SugestaoResponseDTO;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.materia.service.MateriaService;
import br.com.ifba.prg04.focusflow.sessaoestudos.model.SessaoEstudo;
import br.com.ifba.prg04.focusflow.sessaoestudos.service.SessaoEstudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SugestaoService {

    private final SessaoEstudoService sessaoEstudoService;
    private final MateriaService materiaService;
    private final OpenAiClient openAiClient;

    private static final String PROMPT_SISTEMA = """
            Você é o assistente de estudos do FocusFlow. Você recebe um resumo estatístico do
            histórico de estudos do usuário (nome da matéria, minutos totais estudados e dias
            desde a última sessão para cada matéria cadastrada).
            Escreva UMA única sugestão curta (2 a 3 frases), em português, direta e motivadora,
            indicando qual matéria o usuário deveria estudar agora e sugerindo uma duração de
            sessão em minutos (entre 15 e 50).
            Priorize a matéria com mais dias sem estudo ou com menos minutos totais.
            Use apenas os dados fornecidos no resumo. NUNCA invente números, percentuais de erro,
            tópicos específicos (como "limites laterais") ou qualquer estatística que não esteja
            no resumo enviado.
            Responda apenas com o texto da sugestão, sem formatação markdown.
            """;

    public SugestaoResponseDTO gerarSugestao(Long usuarioId) {
        List<Materia> materias = materiaService.listarPorUsuario(usuarioId);

        if (materias.isEmpty()) {
            return new SugestaoResponseDTO(
                    "Cadastre sua primeira matéria e comece uma sessão de estudos para receber sugestões personalizadas!");
        }

        String resumoEstatistico = materias.stream()
                .map(this::descreverMateria)
                .collect(Collectors.joining("\n"));

        String mensagem = openAiClient.gerarResposta(PROMPT_SISTEMA, resumoEstatistico);
        return new SugestaoResponseDTO(mensagem.trim());
    }

    private String descreverMateria(Materia materia) {
        List<SessaoEstudo> sessoes = sessaoEstudoService.listarPorMateria(materia.getId());

        long totalMinutos = sessoes.stream().mapToLong(SessaoEstudo::getDuracaoMinutos).sum();

        Optional<LocalDate> ultimaData = sessoes.stream()
                .map(SessaoEstudo::getData)
                .max(LocalDate::compareTo);

        String situacaoRecencia = ultimaData
                .map(data -> ChronoUnit.DAYS.between(data, LocalDate.now()) + " dia(s) desde a última sessão")
                .orElse("nunca estudada");

        return "- %s: %d minutos estudados no total, %s".formatted(materia.getNome(), totalMinutos, situacaoRecencia);
    }
}
