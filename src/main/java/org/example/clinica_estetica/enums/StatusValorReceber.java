package org.example.clinica_estetica.enums;

public enum StatusValorReceber {

    ABERTA("Aberta"),
    QUITADA("Quitada");

    private String descricao;

    private StatusValorReceber(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
