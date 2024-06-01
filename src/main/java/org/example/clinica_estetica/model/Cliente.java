package org.example.clinica_estetica.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa{

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String cpf;

    private String cidade;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
