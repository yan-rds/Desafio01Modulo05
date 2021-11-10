package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.dtos.CadastroDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper conversor;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarConta (@RequestBody CadastroDTO cadastroDTO){
        Conta conta = conversor.map(cadastroDTO, Conta.class);
        contaService.cadastrarConta(conta);
    }


}
