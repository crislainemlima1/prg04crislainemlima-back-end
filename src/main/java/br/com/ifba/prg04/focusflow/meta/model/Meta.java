package br.com.ifba.prg04.focusflow.meta.model;

import br.com.ifba.prg04.focusflow.common.PersistentEntity;
import br.com.ifba.prg04.focusflow.materia.model.Materia;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

// Entidade que representa uma meta de estudo do usuário para uma matéria
@Entity
@Table(name = "metas")
@Data
@EqualsAndHashCode(callSuper = true)
public class Meta extends PersistentEntity {

    private Integer horasObjetivo;

    private LocalDate dataLimite;

    private Boolean concluida = false;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;


}
