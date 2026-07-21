package br.com.ifba.prg04.focusflow.flashcard.model;

import br.com.ifba.prg04.focusflow.common.PersistentEntity;
import br.com.ifba.prg04.focusflow.ia.resumo.model.Resumo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "flashcards")
@Data
@EqualsAndHashCode(callSuper = true)
public class Flashcard extends PersistentEntity {

    @Column(columnDefinition = "TEXT")
    private String pergunta;

    @Column(columnDefinition = "TEXT")
    private String resposta;

    // Relação muitos-para-um: vários flashcards pertencem a um resumo
    @ManyToOne
    @JoinColumn(name = "resumo_id", nullable = false)
    private Resumo resumo;
}
