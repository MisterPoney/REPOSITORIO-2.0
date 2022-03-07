package mongodbexercicio.marcos.exerciciomarcos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import mongodbexercicio.marcos.exerciciomarcos.service.PessoaService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;
    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.pessoaController);
    }

    private static final String PESSOA_ID = "123";
    private static final String PESSOA_TELEFONE= "(11)94445-4643";
    private static final String PESSOA_NOME= "Marcos Andreo";
    private static final String PESSOA_IDADE = "18";

    @Test
    public void deveObterTodos() throws Exception {
        Pessoa pessoa = new Pessoa();
        List<Pessoa> pessoasEsperadas = Arrays.asList(pessoa);
        doReturn(pessoasEsperadas).when(pessoaService).obterTodos();
        List<Pessoa> actualPessoas = pessoaController.obterTodos();
        assertEquals(pessoasEsperadas, actualPessoas);

    }

    @Test
    public void obterPorCodigo() {
        Pessoa pessoa = new Pessoa();
        pessoaController.obterPorCodigo(PESSOA_ID);
        verify(pessoaService, times(1)).obterPorCodigo(any());
    }


    @Test
    public void criar() {
        Pessoa pessoa = new Pessoa();
        pessoaController.criar(pessoa);
        pessoa.setCodigo(PESSOA_ID);
        pessoa.setTelefone(PESSOA_TELEFONE);
        pessoa.setIdade(PESSOA_IDADE);
        pessoa.setNome(PESSOA_NOME);
        verify(pessoaService, times(1)).criar(any());
        //como verificar se está retornando o HTTPStatus correto?
    }
    @Test
    public void senaocriar(){
        Pessoa pessoa = new Pessoa();
        pessoaController.criar(pessoa);
        pessoa = null;
        if (pessoa==null){

        }
    }

    @Test
    public void deletar() {
        Pessoa pessoa = new Pessoa();
        pessoaController.deletar(pessoa);
        pessoa.setCodigo(PESSOA_ID);
        pessoa.setTelefone(PESSOA_TELEFONE);
        pessoa.setIdade(PESSOA_IDADE);
        pessoa.setNome(PESSOA_NOME);
        verify(pessoaService, times(1)).deletar(any());

    }

    @Test
    public void naoDeletou() {
        Pessoa pessoa = new Pessoa();
        pessoaController.deletar(pessoa);
        pessoa.setCodigo(PESSOA_ID);
        pessoa.setTelefone(PESSOA_TELEFONE);
        pessoa.setIdade(PESSOA_IDADE);
        pessoa.setNome(PESSOA_NOME);

        throw new IllegalArgumentException("Pessoa não foi deletada");
    }


    @Test
    public void deveAlterar() {
        Pessoa pessoa = new Pessoa();
        pessoaController.alterar(pessoa);
        verify(pessoaService, times(1)).alterar(any());

    }

    @Test
    public void seNaoAlterar() {
        Pessoa pessoa = new Pessoa();
        pessoaController.alterar(pessoa);
        pessoa = null;
        if (pessoa == null) {
            throw new IllegalArgumentException("Erro ao deletar a pessoa");
        }

    }


    //como usar esse valor para os outros métodos??
    @Test
    public void criarPessoaNaoNula() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(PESSOA_ID);
        pessoa.setTelefone(PESSOA_TELEFONE);
        pessoa.setIdade(PESSOA_IDADE);
        pessoa.setNome(PESSOA_NOME);
    }
    @Test
    public void criarPessoaNula(){
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(null);
        pessoa.setTelefone(null);
        pessoa.setIdade(null);
        pessoa.setNome(null);
    }
}
