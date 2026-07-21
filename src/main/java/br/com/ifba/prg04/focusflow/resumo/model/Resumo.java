package br.com.ifba.prg04.focusflow.resumo.model;

import br.com.ifba.prg04.focusflow.common.PersistentEntity;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


// Entidade que representa um resumo gerado pela IA e salvo no banco
@Entity
@Table(name = "resumos")
@Data
@EqualsAndHashCode(callSuper = true)

public class Resumo extends PersistentEntity {

    @Column(columnDefinition = "TEXT")
    private String ideiaCentral;

    @Column(columnDefinition = "TEXT")
    private String conceitosChave;

    @Column(columnDefinition = "TEXT")
    private String conexoes;

    private String dificuldadeEstimada;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;
}
