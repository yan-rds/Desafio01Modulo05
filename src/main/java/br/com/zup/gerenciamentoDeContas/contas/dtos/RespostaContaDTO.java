package br.com.zup.gerenciamentoDeContas.contas.dtos;

import br.com.zup.gerenciamentoDeContas.contas.enums.Status;
import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class RespostaContaDTO {
    int id;
    String nome;
    double valor;
    Tipo tipo;
    LocalDate dataDeVencimento;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime dataDePagamento;
    Status status;

    public RespostaContaDTO() {
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

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public LocalDateTime getDataDePagamento() {
        return dataDePagamento;
    }

    public void setDataDePagamento(LocalDateTime dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
