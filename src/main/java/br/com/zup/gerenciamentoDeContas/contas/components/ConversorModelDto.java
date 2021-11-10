package br.com.zup.gerenciamentoDeContas.contas.components;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConversorModelDto {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
