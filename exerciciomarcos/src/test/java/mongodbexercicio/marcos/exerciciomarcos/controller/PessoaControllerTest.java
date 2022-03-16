package mongodbexercicio.marcos.exerciciomarcos.controller;

import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import mongodbexercicio.marcos.exerciciomarcos.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void should_callTheObterTodosInService_when_obterTodos_given_aPerson() throws Exception {
        Pessoa pessoa = criarPessoa();
        List<Pessoa> pessoasEsperadas = Arrays.asList(pessoa);
        doReturn(pessoasEsperadas).when(pessoaService).obterTodos();
        ResponseEntity<List<Pessoa>> response = pessoaController.obterTodos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoasEsperadas, response.getBody());

    }

    @Test
    public void should_callTheObterPorCodigoInService_when_obterPorCodigo_given_aPerson() {
        Pessoa pessoa = criarPessoa();
        when(pessoaService.obterPorCodigo(PESSOA_ID)).thenReturn(pessoa);
        ResponseEntity<Pessoa> response = pessoaController.obterPorCodigo(PESSOA_ID);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(pessoaService.obterPorCodigo(PESSOA_ID),response.getBody());

    }

    @Test
    public void should_callTheCriarInService_when_criar_given_aPerson() {
        Pessoa pessoa = criarPessoa();
        pessoaController.criar(pessoa);
        verify(pessoaService, times(1)).criar(pessoa);

    }

    @Test
    public void should_callTheDeletarInService_when_deletar_given_aPerson() {
        Pessoa pessoa = criarPessoa();
        pessoaController.deletar(pessoa);
        verify(pessoaService, times(1)).deletar(pessoa);
    }


    @Test
    public void should_callTheAlterarInService_when_alterar_given_aPerson() {
        Pessoa pessoa = criarPessoa();
        pessoaController.alterar(pessoa);
        verify(pessoaService, times(1)).alterar(pessoa);

    }

    public Pessoa criarPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(PESSOA_ID);
        pessoa.setTelefone(PESSOA_TELEFONE);
        pessoa.setIdade(PESSOA_IDADE);
        pessoa.setNome(PESSOA_NOME);
        return pessoa;
    }
}
