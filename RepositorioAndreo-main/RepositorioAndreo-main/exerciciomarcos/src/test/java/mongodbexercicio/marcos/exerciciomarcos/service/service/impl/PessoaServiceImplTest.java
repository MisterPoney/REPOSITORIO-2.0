package mongodbexercicio.marcos.exerciciomarcos.service.service.impl;

import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import mongodbexercicio.marcos.exerciciomarcos.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceImplTest {
    @InjectMocks
    private PessoaServiceImpl pessoaService;
    @Mock
    private PessoaRepository pessoaRepository;
    private static final String PESSOA_ID = "123";

    @Test
    public void obterTodos() {
        Pessoa pessoa = new Pessoa();
        List<Pessoa> pessoasEsperadas = Arrays.asList(pessoa);
        doReturn(pessoasEsperadas).when(pessoaRepository).findAll();
        List<Pessoa> actualPessoas = pessoaService.obterTodos();
        assertThat(actualPessoas).isEqualTo(pessoasEsperadas);
    }

    @Test
    public void senaoobtertodos() {
        Pessoa pessoa = new Pessoa();
        List<Pessoa> pessoasEsperadas = Arrays.asList(pessoa);
        doReturn(pessoasEsperadas).when(pessoaRepository).findAll();
        List<Pessoa> actualPessoas = pessoaService.obterTodos();
        if (actualPessoas != pessoasEsperadas) {
            throw new IllegalArgumentException("Está Pessoa não existe");
        }
    }

    @Test
    void obterPorCodigo() {
        Pessoa pessoa = new Pessoa();
        Mockito.when(pessoaRepository.findById(PESSOA_ID)).thenReturn(Optional.of(pessoa));
        assertThat(pessoaService.obterPorCodigo(PESSOA_ID)).isEqualTo(pessoa);
    }

    @Test
    public void criar() {
        Pessoa pessoa = new Pessoa();
        pessoaService.criar(pessoa);
        verify(pessoaRepository, times(1)).save(any());
    }

    @Test
    void deletar() {
        Pessoa pessoa = new Pessoa();
        pessoaService.deletar(pessoa);
        verify(pessoaRepository, times(1)).delete(any());
    }

    @Test
    void alterar() {
        Pessoa pessoa = new Pessoa();
        pessoaService.alterar(pessoa);
        verify(pessoaRepository, times(1)).save(any());
    }
}