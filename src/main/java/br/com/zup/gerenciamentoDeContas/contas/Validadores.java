package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.enums.Status;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.ContaJaPaga;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.StatusInvalido;

import java.time.LocalDate;

public class Validadores {

    public static boolean validarDataDePagamento(LocalDate dataDeVencimento){
        if (dataDeVencimento.isBefore(LocalDate.now())){
            return true;
        }
        return false;
    }

    public static void validarStatus(Conta conta, Status status){
        if (!status.equals(Status.PAGO)){
            throw new StatusInvalido("Status inserido inválido");
        }
        else if (conta.status.equals(Status.PAGO)){
            throw new ContaJaPaga("Esta conta já foi paga");
        }
    }
}
