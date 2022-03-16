package mongodbexercicio.marcos.exerciciomarcos.service.service.impl;

import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import mongodbexercicio.marcos.exerciciomarcos.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceImplTest {
    @InjectMocks
    private PessoaServiceImpl pessoaService;
    @Mock
    private PessoaRepository pessoaRepository;
    private static final String PESSOA_ID = "123";
    private static final String PESSOA_TELEFONE= "(11)94445-4643";
    private static final String PESSOA_NOME= "Marcos Andreo";
    private static final String PESSOA_IDADE = "18";

    @Test
    public void should_callTheFindAllInRepository_when_findAll_given_aPerson() {
       /* Pessoa pessoa = new Pessoa();
        List<Pessoa> pessoasEsperadas = Arrays.asList(pessoa);
        doReturn(pessoasEsperadas).when(pessoaRepository).findAll();
        List<Pessoa> actualPessoas = pessoaService.obterTodos();
        assertEquals(actualPessoas,pessoasEsperadas);*/
        //ou
      List all = new LinkedList();
        all.add(criarPessoa());
        all.add(criarPessoa2());
        when(pessoaRepository.findAll()).thenReturn(all);
        List result = pessoaService.obterTodos();
        verify(pessoaRepository).findAll();
        assertEquals(pessoaRepository.findAll(),pessoaService.obterTodos());

    }

    @Test
    void should_callTheFindByIdInRepository_when_findById_given_aPerson(){
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.findById(PESSOA_ID)).thenReturn(Optional.of(pessoa));
        assertEquals(pessoaService.obterPorCodigo(PESSOA_ID),pessoa);
    }
    @Test
    void should_throwAnIllegalArgumentException_when_findBydId_given_itReturnAndEmptyOptional(){
        doReturn(Optional.empty()).when(pessoaRepository).findById(PESSOA_ID);
        IllegalArgumentException exception =assertThrows(IllegalArgumentException.class, () -> {pessoaService.obterPorCodigo(PESSOA_ID);});
        assertEquals("Esta Pessoa não existe", exception.getMessage());
    }

    @Test
    void should_callTheSaveMethodInRepository_when_save_given_aPerson() {
        Pessoa pessoa = criarPessoa();
       pessoaService.criar(pessoa);
       verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void should_callTheDeleteMethodInRepository_when_delete_given_aPerson() {
        Pessoa pessoa = criarPessoa();
        pessoaService.deletar(pessoa);
        verify(pessoaRepository, times(1)).delete(pessoa);
    }

    @Test
    void should_callTheSaveMethodInRepository_when_update_given_aPerson() {
        Pessoa pessoa = criarPessoa();
        pessoaService.alterar(pessoa);
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    public Pessoa criarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(PESSOA_ID);
        pessoa.setTelefone(PESSOA_TELEFONE);
        pessoa.setIdade(PESSOA_IDADE);
        pessoa.setNome(PESSOA_NOME);
        return pessoa;
    }

    public Pessoa criarPessoa2() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo("12451");
        pessoa.setTelefone("11994568636");
        pessoa.setIdade("18");
        pessoa.setNome("Bruce Wayne");
        return pessoa;
    }

}