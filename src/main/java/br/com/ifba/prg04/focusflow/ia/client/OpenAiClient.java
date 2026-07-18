package br.com.ifba.prg04.focusflow.ia.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

// Cliente HTTP para consumir a API de chat completions da OpenAI.
// Centraliza a chamada para que os 3 serviços de IA (sugestão, resumo, dica)
// só precisem montar o prompt e ler o texto de resposta.
@Component
public class OpenAiClient {

    private final WebClient webClient;

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    public OpenAiClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .build();
    }

    // Envia um prompt de sistema + prompt de usuário e retorna o texto gerado pelo modelo
    public String gerarResposta(String promptSistema, String promptUsuario) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "A chave da API da OpenAI não está configurada. Defina a variável de ambiente OPENAI_API_KEY.");
        }

        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", promptSistema),
                        Map.of("role", "user", "content", promptUsuario)
                ),
                "temperature", 0.7
        );

        Map<?, ?> resposta = webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return extrairConteudo(resposta);
    }

    @SuppressWarnings("unchecked")
    private String extrairConteudo(Map<?, ?> resposta) {
        if (resposta == null) {
            throw new IllegalStateException("A API de IA retornou uma resposta vazia.");
        }

        Object choicesObj = resposta.get("choices");
        if (!(choicesObj instanceof List<?> choices) || choices.isEmpty()) {
            throw new IllegalStateException("A API de IA retornou uma resposta em formato inesperado.");
        }

        Map<String, Object> primeiraEscolha = (Map<String, Object>) choices.get(0);
        Map<String, Object> message = (Map<String, Object>) primeiraEscolha.get("message");
        Object conteudo = message.get("content");

        if (conteudo == null) {
            throw new IllegalStateException("A API de IA não retornou conteúdo de texto.");
        }

        return conteudo.toString();
    }
}
