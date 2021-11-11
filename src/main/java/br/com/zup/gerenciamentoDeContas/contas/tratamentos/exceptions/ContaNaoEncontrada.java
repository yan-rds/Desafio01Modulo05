package br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions;

public class ContaNaoEncontrada extends RuntimeException{

    public ContaNaoEncontrada(String message) {
        super(message);
    }
}
