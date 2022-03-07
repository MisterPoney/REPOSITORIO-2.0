package mongodbexercicio.marcos.exerciciomarcos.controller;

import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import mongodbexercicio.marcos.exerciciomarcos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> obterTodos() {
        return this.pessoaService.obterTodos();
    }

    @GetMapping("/{codigo}")
    public Pessoa obterPorCodigo(@PathVariable String codigo) {
        return this.pessoaService.obterPorCodigo(codigo);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return this.pessoaService.criar(pessoa);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{codigo}")
    public Pessoa deletar(@RequestBody Pessoa pessoa) {
        return this.pessoaService.deletar(pessoa);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{codigo}")
    public Pessoa alterar(@RequestBody Pessoa pessoa) {
        return this.pessoaService.alterar(pessoa);
    }

}
