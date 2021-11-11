package br.com.zup.gerenciamentoDeContas.contas.tratamentos;

import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.ContaJaPaga;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.ContaNaoEncontrada;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.NaoHaContas;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.StatusInvalido;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ContaJaPaga.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemDeErro contaJaPagaException(ContaJaPaga exception){
        return new MensagemDeErro(exception.getMessage());
    }

    @ExceptionHandler(StatusInvalido.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemDeErro StatusInvalidoException(StatusInvalido exception) {
        return new MensagemDeErro(exception.getMessage());
    }

    @ExceptionHandler(ContaNaoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemDeErro contaNaoEncontradaException(ContaNaoEncontrada exception){
        return new MensagemDeErro(exception.getMessage());
    }

    @ExceptionHandler(NaoHaContas.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemDeErro contaNaoEncontradaException(NaoHaContas exception){
        return new MensagemDeErro(exception.getMessage());
    }
}
