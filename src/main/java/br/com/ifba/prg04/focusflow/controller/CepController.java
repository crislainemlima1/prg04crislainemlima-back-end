package br.com.ifba.prg04.focusflow.controller;

import br.com.ifba.prg04.focusflow.client.ViaCepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {

    @Autowired
    private ViaCepClient viaCepClient;

    // GET - Busca endereço pelo CEP
    @GetMapping("/{cep}")
    public String buscarCep(@PathVariable String cep) {
        return viaCepClient.buscarCep(cep);
    }
}
