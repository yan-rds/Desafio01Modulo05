package br.com.zup.gerenciamentoDeContas.contas.tratamentos;

import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.ContaJaPaga;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.ContaNaoEncontrada;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.NaoHaContas;
import br.com.zup.gerenciamentoDeContas.contas.tratamentos.exceptions.StatusInvalido;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ContaJaPaga.class)
    @ResponseStatus(HttpStatus.CONFLICT)
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<MensagemDeErro> manipularErrosDeValidacao(MethodArgumentNotValidException exception){
        List<MensagemDeErro> mensagensDeErro = new ArrayList<>();

        for (FieldError fieldError : exception.getFieldErrors()){
            MensagemDeErro mensagemDeErro = new MensagemDeErro(fieldError.getDefaultMessage());
            mensagensDeErro.add(mensagemDeErro);
        }

        return mensagensDeErro;
    }


}
