package br.com.ifba.prg04.focusflow.sessaoestudos.model;

import br.com.ifba.prg04.focusflow.common.PersistentEntity;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "sessoes_estudo")
@Data
@EqualsAndHashCode(callSuper = true)
public class SessaoEstudo extends PersistentEntity {


    private LocalDate data; // Data em que a sessão de estudo aconteceu
    private Integer duracaoMinutos; // Duração da sessão em minutos

    @ManyToOne // Relacionamento: várias sessões podem estar ligadas a uma única matéria
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

}
