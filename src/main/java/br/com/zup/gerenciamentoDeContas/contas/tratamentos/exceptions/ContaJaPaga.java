package br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions;

public class ContaJaPaga extends RuntimeException{

    public ContaJaPaga(String message) {
        super(message);
    }
}
