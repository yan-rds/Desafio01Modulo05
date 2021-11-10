package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.enums.ContaRepository;
import br.com.zup.gerenciamentoDeContas.contas.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public void cadastrarConta (Conta conta){
        if (Validadores.pagamentoVencido(conta.dataDeVencimento)){
            conta.setStatus(Status.VENCIDA);
        } else{
            conta.setStatus(Status.AGUARDANDO);
        }
        repository.save(conta);
    }
}
