package br.com.ifba.prg04.focusflow.usuario.model;

import br.com.ifba.prg04.focusflow.common.PersistentEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

// Entidade que representa a tabela de usuários no banco de dados
@Entity
@Table(name = "usuarios")
@Data
@EqualsAndHashCode(callSuper = true)
public class Usuario extends PersistentEntity {


    private String nome;

    @Column(nullable = false, unique = true) // E-mail único e obrigatório
    private String email;

    @Column(nullable = false) // Senha obrigatória
    private String senha;
}