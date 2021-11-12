package br.com.zup.gerenciamentoDeContas.contas.dtos;


import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class ContaDTO {
    @NotBlank
    @Size(min = 2, message = "Nome menor que o permitido")
    String nome;
    @NotNull
    @DecimalMin("0.01")
    double valor;
    @NotNull
    Tipo tipo;
    @NotNull
    LocalDate dataDeVencimento;

    public ContaDTO() {
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
