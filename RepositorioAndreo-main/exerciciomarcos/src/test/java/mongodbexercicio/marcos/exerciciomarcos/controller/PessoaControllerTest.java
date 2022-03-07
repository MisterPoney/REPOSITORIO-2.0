package mongodbexercicio.marcos.exerciciomarcos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import mongodbexercicio.marcos.exerciciomarcos.service.PessoaService;
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
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    public void deveObterTodos() throws Exception {
        Pessoa pessoa = new Pessoa();
        List<Pessoa> pessoasEsperadas = Arrays.asList(pessoa);
        doReturn(pessoasEsperadas).when(pessoaService).obterTodos();
        List<Pessoa> actualPessoas = pessoaController.obterTodos();
        assertEquals(pessoasEsperadas, actualPessoas);
        assertThat(HttpStatus.OK);
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
        verify(pessoaService, times(1)).criar(any());
        //como verificar se está retornando o HTTPStatus correto?
        assertThat(HttpStatus.CREATED);
    }


    @Test
    public void deletar() {
        Pessoa pessoa = new Pessoa();
        pessoaController.deletar(pessoa);
        verify(pessoaService, times(1)).deletar(any());
        assertThat(HttpStatus.NO_CONTENT);
    }
    @Test
    public void naodeletou(){

    }


    @Test
    public void deveAlterar() {
        Pessoa pessoa = new Pessoa();
        pessoaController.alterar(pessoa);
        verify(pessoaService, times(1)).alterar(any());
        assertThat(HttpStatus.NO_CONTENT);
    }
}
