package br.com.ifba.prg04.focusflow.conquista.model;

import br.com.ifba.prg04.focusflow.common.PersistentEntity;
import br.com.ifba.prg04.focusflow.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "conquistas")
@EqualsAndHashCode(callSuper = true)
public class Conquista extends PersistentEntity {


    private String titulo;
    private String descricao;
    private LocalDate dataConquista;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
