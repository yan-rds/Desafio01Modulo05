package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.dtos.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    public List exibirCadastros (){
        List <GetCadastroDTO> todosOsCadastros = new ArrayList<>();
        for (Conta conta : contaService.exibirTodasContas()){
            GetCadastroDTO getCadastroDTO = conversor.map(conta, GetCadastroDTO.class);
            todosOsCadastros.add(getCadastroDTO);
        }
        return todosOsCadastros;
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

}
