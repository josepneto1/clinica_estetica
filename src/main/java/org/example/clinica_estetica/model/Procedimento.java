package org.example.clinica_estetica.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "procedimento")
@SequenceGenerator(name = "seq_procedimento", sequenceName = "seq_procedimento", allocationSize = 1, initialValue = 1)
public class Procedimento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_procedimento")
    private Long id;

    private String nome;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
