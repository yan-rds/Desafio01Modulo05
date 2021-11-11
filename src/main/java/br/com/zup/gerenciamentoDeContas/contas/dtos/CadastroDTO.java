package br.com.zup.gerenciamentoDeContas.contas.dtos;


import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


public class CadastroDTO {
    @NotBlank
    String nome;
    @NotBlank
    double valor;
    @NotBlank
    Tipo tipo;
    @NotBlank
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
