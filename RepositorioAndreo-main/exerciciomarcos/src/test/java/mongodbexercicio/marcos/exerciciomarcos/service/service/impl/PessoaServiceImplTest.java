package mongodbexercicio.marcos.exerciciomarcos.service.service.impl;

import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import mongodbexercicio.marcos.exerciciomarcos.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void obterTodos() {
        Pessoa pessoa = new Pessoa();
        List<Pessoa> pessoasEsperadas = Arrays.asList(pessoa);
        doReturn(pessoasEsperadas).when(pessoaRepository).findAll();
        List<Pessoa> actualPessoas = pessoaService.obterTodos();
        assertEquals(actualPessoas,pessoasEsperadas);
    }

    @Test
    public void senaoobtertodos() {
        Pessoa pessoa = new Pessoa();
        List<Pessoa> pessoasEsperadas = Arrays.asList(pessoa);
        doReturn(pessoasEsperadas).when(pessoaRepository).findAll();
        List<Pessoa> actualPessoas = pessoaService.obterTodos();
        if (actualPessoas != pessoasEsperadas) {
            throw new IllegalArgumentException("As pessoas não foram encontradas");
        }
    }

    @Test
    void obterPorCodigo() {
        Pessoa pessoa = new Pessoa();
        Mockito.when(pessoaRepository.findById(PESSOA_ID)).thenReturn(Optional.of(pessoa));
        assertEquals(pessoaService.obterPorCodigo(PESSOA_ID),pessoa);
    }
    @Test
    void senaoobterPorCodigo() {
        Pessoa pessoa = new Pessoa();
        Mockito.when(pessoaRepository.findById(PESSOA_ID)).thenReturn(Optional.of(pessoa));
        if (pessoaService.obterPorCodigo(PESSOA_ID)!=pessoa){
            throw new IllegalArgumentException("Essas pessoa não existe");
        }
    }

    @Test
    void devecriar() {
        Pessoa pessoa = new Pessoa();
        pessoaService.criar(pessoa);
        pessoa.setCodigo(PESSOA_ID);
        pessoa.setTelefone(PESSOA_TELEFONE);
        pessoa.setIdade(PESSOA_IDADE);
        pessoa.setNome(PESSOA_NOME);
        verify(pessoaService, times(1)).criar(any());
    }

    @Test
    void deletar() {
        Pessoa pessoa = new Pessoa();
        pessoaService.deletar(pessoa);
        verify(pessoaRepository, times(1)).delete(any());
    }
    @Test
    public void naodeletou(){
        Pessoa pessoa = new Pessoa();
        pessoaService.deletar(pessoa);
        pessoa.setCodigo(PESSOA_ID);
        pessoa.setTelefone(PESSOA_TELEFONE);
        pessoa.setIdade(PESSOA_IDADE);
        pessoa.setNome(PESSOA_NOME);

    }

    @Test
    void alterar() {
        Pessoa pessoa = new Pessoa();
        pessoaService.alterar(pessoa);
        verify(pessoaRepository, times(1)).save(any());

    }
    @Test
    public void devearrumartelefone(){
        String telefone = ("(11)9554-4567");
        telefone = telefone.replaceAll("[^0-9]", "");
        assertEquals(telefone,"1195544567");
    }
    //Como fazer teste de falha do normalizetelefone
    @Test
    public void naoarrumoutelefone(){
        String telefone = ("(11)9554-4567");
        telefone = telefone.replaceAll("[^0-9-]", "");
        if (telefone!="1195544567"){
            throw new IllegalArgumentException("Essas pessoa não existe");
        }
    }

}