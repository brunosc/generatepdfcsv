package br.com.tests.domain;

public class NotaFiscal {

    private int id;
    private String descricao;
    private String complemento;
    private double valor;

    public NotaFiscal(int id, String descricao, String complemento, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.complemento = complemento;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }


    public String getDescricao() {
        return descricao;
    }

    public String getComplemento() {
        return complemento;
    }

    public double getValor() {
        return valor;
    }

}
