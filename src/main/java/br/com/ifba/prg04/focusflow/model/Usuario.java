package br.com.ifba.prg04.focusflow.model;

import jakarta.persistence.*;
import lombok.Data;

// Entidade que representa a tabela de usuários no banco de dados
@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID gerado automaticamente
    private Long id;

    private String nome;

    @Column(nullable = false, unique = true) // E-mail único e obrigatório
    private String email;

    @Column(nullable = false) // Senha obrigatória
    private String senha;
}