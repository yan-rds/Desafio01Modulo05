package br.com.zup.gerenciamentoDeContas.contas.dtos;

import br.com.zup.gerenciamentoDeContas.contas.enums.Status;
import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CadastroDTO {
    int id;
    String nome;
    double valor;
    Tipo tipo;
    LocalDate dataDeVencimento;
    LocalDateTime dataDePagamento;
    Status status;



}
