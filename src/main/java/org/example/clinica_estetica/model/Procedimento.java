package org.example.clinica_estetica.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "procedimento")
@SequenceGenerator(name = "seq_procedimento", sequenceName = "seq_procedimento", allocationSize = 1, initialValue = 1)
public class Procedimento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_procedimento")
    private Long id;

    private String nome;

    private BigDecimal preco;

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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
