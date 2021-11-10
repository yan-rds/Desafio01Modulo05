package br.com.zup.gerenciamentoDeContas.contas.enums;

import br.com.zup.gerenciamentoDeContas.contas.Conta;
import org.springframework.data.repository.CrudRepository;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

}
