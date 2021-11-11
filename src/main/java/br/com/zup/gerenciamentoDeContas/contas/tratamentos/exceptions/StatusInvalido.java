package br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions;

public class StatusInvalido extends RuntimeException{

    public StatusInvalido(String message) {
        super(message);
    }
}
