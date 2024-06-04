package org.example.clinica_estetica.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 20, nullable = false)
    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
