package br.com.zup.gerenciamentoDeContas.contas.dtos;

import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;


public class RespostaCadastroDTO {
    int id;
    String nome;
    double valor;
    Tipo tipo;

    public RespostaCadastroDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
