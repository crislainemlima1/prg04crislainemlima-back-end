package br.com.ifba.prg04.focusflow.sessaoEstudos.model;

import br.com.ifba.prg04.focusflow.materia.model.Materia;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "sessoes_estudo")
@Data
public class SessaoEstudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private Integer duracaoMinutos;

    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

}
