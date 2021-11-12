package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.enums.Status;
import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.ContaNaoEncontrada;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.NaoHaContas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public void cadastrarConta (Contas contas){
        if (Validadores.pagamentoVencido(contas.dataDeVencimento)){
            contas.setStatus(Status.VENCIDA);
        } else{
            contas.setStatus(Status.AGUARDANDO);
        }
        repository.save(contas);
    }

    public List<Contas> exibirTodasContas (){
        List<Contas> lista = (List<Contas>) repository.findAll();
        if (lista.isEmpty()){
            throw new NaoHaContas("Não há contas cadastradas");
        }
        return (List<Contas>) repository.findAll();
    }

    public Contas atualizarPagamento (int id, Status status){
        Contas contasAAtualizar = localizarConta(id);
        Validadores.statusValido(contasAAtualizar, status);
        contasAAtualizar.setStatus(status);
        contasAAtualizar.setDataDePagamento(LocalDateTime.now());
        repository.save(contasAAtualizar);
        return contasAAtualizar;
    }

    public Contas localizarConta (int id){
        Optional <Contas> contaLocaizadarepository = repository.findById(id);
            if (contaLocaizadarepository.isPresent()){
                return contaLocaizadarepository.get();
            }

        throw new ContaNaoEncontrada("Conta não localizada");
    }

    public List<Contas> identificarFiltroCorreto (Optional<Double> valor, Optional<Status> status, Optional<Tipo> tipo){
        List<Contas> listaFiltrada = new ArrayList<>();
        if (status.isPresent()){
            listaFiltrada.addAll(filtrarPorStatus(status.get()));
        }
        else if (tipo.isPresent()){
            listaFiltrada.addAll(filtrarPorTipo(tipo.get()));
        }
        else valor.ifPresent(valorAproximado -> listaFiltrada.addAll(filtrarPorValorAproximado(valorAproximado)));
        return listaFiltrada;
    }

    public List<Contas> filtrarPorStatus (Status status){
        return (List<Contas>) repository.findAllByStatus(status);
    }



    public List<Contas> filtrarPorTipo (Tipo tipo){
        return (List<Contas>) repository.findAllByTipo(tipo);
    }

    public List<Contas> filtrarPorValorAproximado (double valor){
        List<Contas> valorAproximado = (List<Contas>) repository.findAllAproximatedContas(valor);
        return valorAproximado;
    }

    public void deletarConta (int id){
        repository.deleteById(id);
    }


}
