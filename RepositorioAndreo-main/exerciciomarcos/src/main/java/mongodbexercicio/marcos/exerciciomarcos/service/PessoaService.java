package mongodbexercicio.marcos.exerciciomarcos.service;
import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;

import java.util.List;

public interface PessoaService {
    List<Pessoa> obterTodos();

    Pessoa obterPorCodigo(String codigo);

    Pessoa criar (Pessoa pessoa);

    Pessoa deletar(Pessoa pessoa);

    Pessoa alterar(Pessoa pessoa);
}