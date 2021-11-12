package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.dtos.*;
import br.com.zup.gerenciamentoDeContas.contas.enums.Status;
import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper conversor;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespostaContaDTO cadastrarConta (@RequestBody @Valid ContaDTO contaDTO){
        Conta conta = conversor.map(contaDTO, Conta.class);
        contaService.cadastrarConta(conta);
        RespostaContaDTO respostaContaDTO = conversor.map(conta, RespostaContaDTO.class);
        return respostaContaDTO;
    }

    @GetMapping
    public List exibirCadastros (@RequestParam Optional<Double> valor,
                                 @RequestParam Optional<Status> status,
                                 @RequestParam Optional<Tipo> tipo){
        List <ContaListadaDTO> listaExibida = new ArrayList<>();
        /* Este If verifica se qualquer um dos @RequestParam foi preenchido, caso sim, ele chama o método
           que verifica qual deles foi inserido, os transforma em DTO e os adiciona à lista.*/
        if (status.isPresent() | tipo.isPresent() | valor.isPresent()){
            for (Conta conta : contaService.identificarFiltroCorreto(valor, status, tipo)){
                ContaListadaDTO contaListadaDTO = conversor.map(conta, ContaListadaDTO.class);
                listaExibida.add(contaListadaDTO);
            }
        } else {/* Caso não haja parâmetros, ele executa o método que lista todas as contas, os converte e
                   adiciona a nova lista*/
            for (Conta conta : contaService.exibirTodasContas()) {
                ContaListadaDTO contaListadaDTO = conversor.map(conta, ContaListadaDTO.class);
                listaExibida.add(contaListadaDTO);
            }
        }
        return listaExibida;
    }
    @GetMapping("/{id}")
    public RespostaContaDTO exibirContaEspecifica (@PathVariable int id){
        Conta contaLocalizada = contaService.localizarConta(id);
        return conversor.map(contaLocalizada, RespostaContaDTO.class);
    }

    @PutMapping("/{id}")
    public RespostaContaDTO atualizarPagamento (@PathVariable int id, @RequestBody StatusPagamentoDTO status){
        Conta contaAtualizada = contaService.atualizarPagamento(id, status.getStatus());
        RespostaContaDTO respostaContaDTO = conversor.map(contaAtualizada, RespostaContaDTO.class);
        return respostaContaDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConta (@PathVariable int id){
        contaService.deletarConta(id);
    }

}
