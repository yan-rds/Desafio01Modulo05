package br.com.zup.gerenciamentoDeContas.contas;

import br.com.zup.gerenciamentoDeContas.contas.enums.Status;
import br.com.zup.gerenciamentoDeContas.contas.enums.Tipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContaRepository extends CrudRepository<Contas, Integer> {

    List<Contas> findAllByStatus (Status status);
    List<Contas> findAllByTipo (Tipo tipo);
    @Query(value = "SELECT * FROM contas WHERE VALOR BETWEEN :valor*0.7 AND :valor*1.3", nativeQuery = true)
    List <Contas> findAllAproximatedContas (double valor);




}
