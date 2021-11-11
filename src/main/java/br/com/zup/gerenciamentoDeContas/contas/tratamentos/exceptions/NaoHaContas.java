package br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions;

public class NaoHaContas extends RuntimeException {

    public NaoHaContas(String message) {
        super(message);
    }
}
