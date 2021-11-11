package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.enums.Status;

import java.time.LocalDate;

public class Validadores {

    public static boolean pagamentoVencido(LocalDate dataDeVencimento){
        if (dataDeVencimento.isBefore(LocalDate.now())){
            return true;
        }
        return false;
    }

    public static void statusValido (Conta conta, Status status){
        if (!status.equals(Status.PAGO)){
            throw new RuntimeException();
        }
        else if (conta.status.equals(Status.PAGO)){
            throw new RuntimeException();
        }

    }



}
