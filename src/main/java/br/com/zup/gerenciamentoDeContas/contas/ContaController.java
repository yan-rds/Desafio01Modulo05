package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.dtos.*;
import br.com.zup.gerenciamentoDeContas.contas.enums.Status;
import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
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
    public RespostaCadastroDTO cadastrarConta (@RequestBody @Valid CadastroDTO cadastroDTO){
        Conta conta = conversor.map(cadastroDTO, Conta.class);
        contaService.cadastrarConta(conta);
        RespostaCadastroDTO respostaCadastroDTO = conversor.map(conta, RespostaCadastroDTO.class);
        return respostaCadastroDTO;
    }

    @GetMapping
    public List exibirCadastros (@RequestParam Optional<Double> valor, @RequestParam Optional<Status> status, @RequestParam Optional<Tipo> tipo){
        List <GetCadastroDTO> listaExibida = new ArrayList<>();
        if (status.isPresent() | tipo.isPresent() | valor.isPresent()){
            for (Conta conta : contaService.identificarFiltroCorreto(valor, status, tipo)){
                GetCadastroDTO getCadastroDTO = conversor.map(conta, GetCadastroDTO.class);
                listaExibida.add(getCadastroDTO);
            }
        } else {
            for (Conta conta : contaService.exibirTodasContas()) {
                GetCadastroDTO getCadastroDTO = conversor.map(conta, GetCadastroDTO.class);
                listaExibida.add(getCadastroDTO);
            }
        }
        return listaExibida;
    }
    @GetMapping("/{id}")
    public RespostaAtualizacaoDTO exibirContaEspecifica (@PathVariable int id){
        Conta contaLocalizada = contaService.localizarConta(id);
        return conversor.map(contaLocalizada, RespostaAtualizacaoDTO.class);
    }

    @PutMapping("/{id}")
    public RespostaAtualizacaoDTO atualizarPagamento (@PathVariable int id, @RequestBody StatusPagamentoDTO status){
        Conta contaAtualizada = contaService.atualizarPagamento(id, status.getStatus());
        RespostaAtualizacaoDTO respostaAtualizacaoDTO = conversor.map(contaAtualizada, RespostaAtualizacaoDTO.class);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = contaAtualizada.dataDePagamento.format(format);
        respostaAtualizacaoDTO.setDataDePagamento(formatDateTime);
        return respostaAtualizacaoDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConta (@PathVariable int id){
        contaService.deletarConta(id);
    }

}
