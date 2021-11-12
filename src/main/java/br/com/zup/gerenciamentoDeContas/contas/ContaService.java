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

    public void cadastrarConta (Conta conta){
        if (Validadores.validarDataDePagamento(conta.dataDeVencimento)){
            conta.setStatus(Status.VENCIDA);
        } else{
            conta.setStatus(Status.AGUARDANDO);
        }
        repository.save(conta);
    }

    public List<Conta> exibirTodasContas (){
        List<Conta> lista = (List<Conta>) repository.findAll();
        if (lista.isEmpty()){
            throw new NaoHaContas("Não há contas cadastradas");
        }
        return (List<Conta>) repository.findAll();
    }

    public Conta atualizarPagamento (int id, Status status){
        Conta contaAAtualizar = localizarConta(id);
        Validadores.validarStatus(contaAAtualizar, status);
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
        throw new ContaNaoEncontrada("Conta não localizada");
    }

         /* Este método é chamado caso um @RequestParam seja inserido na Controller, sua função é identificar
            o parâmetro correto e retornar uma lista filtrada por este parâmetro*/
    public List<Conta> identificarFiltroCorreto (Optional<Double> valor,
                                                 Optional<Status> status,
                                                 Optional<Tipo> tipo){
        List<Conta> listaFiltrada = new ArrayList<>();
        /* A Lambda substitui o if convencional por ifPresente, sua função é a partir de um parâmetro
           adicionar à listaFiltrada todos os itens do método filtrarPor equivalente.*/
        status.ifPresent(statusParametro -> listaFiltrada.addAll(filtrarPorStatus(statusParametro)));
        tipo.ifPresent(tipoParametro -> listaFiltrada.addAll(filtrarPorTipo(tipoParametro)));
        valor.ifPresent(valorAproximado -> listaFiltrada.addAll(filtrarPorValorAproximado(valorAproximado)));
        return listaFiltrada;
    }

    public List<Conta> filtrarPorStatus (Status status){
        return (List<Conta>) repository.findAllByStatus(status);
    }



    public List<Conta> filtrarPorTipo (Tipo tipo){
        return (List<Conta>) repository.findAllByTipo(tipo);
    }

    public List<Conta> filtrarPorValorAproximado (double valor){
        List<Conta> valorAproximado = (List<Conta>) repository.findAllAproximatedContas(valor);
        return valorAproximado;
    }

    public void deletarConta (int id){
        repository.deleteById(id);
    }


}
