package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Conta> exibirTodasContas (){
        return (List<Conta>) repository.findAll();
    }

    public Conta atualizarPagamento (int id, Status status){
        Conta contaAAtualizar = localizarConta(id);
        Validadores.statusValido(contaAAtualizar, status);
        contaAAtualizar.setStatus(status);
        contaAAtualizar.setDataDePagamento(LocalDateTime.now());
        repository.save(contaAAtualizar);
        return contaAAtualizar;
    }

    public Conta localizarConta (int id){
        Optional <Conta> contaLocaizadarepository = repository.findById(id);
            if (contaLocaizadarepository.isPresent()){
                return contaLocaizadarepository.get();
            }

        throw new RuntimeException("Não encontrado");
    }



}
