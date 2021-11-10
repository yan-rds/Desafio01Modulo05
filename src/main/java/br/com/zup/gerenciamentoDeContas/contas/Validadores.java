package br.com.zup.gerenciamentoDeContas.contas;

import java.time.LocalDate;

public class Validadores {

    public static boolean pagamentoVencido(LocalDate dataDeVencimento){
        if (dataDeVencimento.isBefore(LocalDate.now())){
            return true;
        }
        return false;
    }
}
