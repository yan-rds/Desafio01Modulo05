package br.com.zup.gerenciamentoDeContas.contas.dtos;


import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;
import java.time.LocalDate;


public class CadastroDTO {
    String nome;
    double valor;
    Tipo tipo;
    LocalDate dataDeVencimento;

    public CadastroDTO() {
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

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

}
