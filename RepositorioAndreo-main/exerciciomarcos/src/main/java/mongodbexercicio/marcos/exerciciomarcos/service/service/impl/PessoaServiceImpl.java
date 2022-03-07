package mongodbexercicio.marcos.exerciciomarcos.service.service.impl;

import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import mongodbexercicio.marcos.exerciciomarcos.repository.PessoaRepository;
import mongodbexercicio.marcos.exerciciomarcos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Pessoa> obterTodos() {
        return this.pessoaRepository.findAll();
    }

    @Override
    public Pessoa obterPorCodigo(String codigo) {
        return this.pessoaRepository.
                findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Está Pessoa não existe"));
    }

    @Override
    public Pessoa criar(Pessoa pessoa) {
        normalizeTelefone(pessoa);
        return this.pessoaRepository.save(pessoa);
    }

    private void normalizeTelefone(Pessoa pessoa) {
        pessoa.setTelefone(pessoa.getTelefone().replaceAll("[^0-9]", ""));
    }


    @Override
    public Pessoa deletar(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
        return pessoa;
    }

    @Override
    public Pessoa alterar(Pessoa pessoa) {
        normalizeTelefone(pessoa);
        return this.pessoaRepository.save(pessoa);
    }
}
