package br.com.ifba.prg04.focusflow.materia.model;

import br.com.ifba.prg04.focusflow.common.PersistentEntity;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "materias")
@Data
@EqualsAndHashCode(callSuper = true)
public class Materia extends PersistentEntity {


    private String nome;
    private Integer metaHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
