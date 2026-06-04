package br.com.ifba.prg04.focusflow.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


// Cliente HTTP para consumir a API do viaCEP
@Component
public class ViaCepClient {

    private final WebClient webClient;

    // Configurando a URL base da API
    public ViaCepClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://viacep.com.br")
                .build();
    }

    public String buscarCep(String cep){
        return webClient.get()
                .uri("/ws/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
