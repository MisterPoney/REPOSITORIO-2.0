package mongodbexercicio.marcos.exerciciomarcos.controller;

import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import mongodbexercicio.marcos.exerciciomarcos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Pessoa>> obterTodos() {
        return ResponseEntity
                .ok()
                .body(this.pessoaService.obterTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity obterPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok().body(this.pessoaService.obterPorCodigo(codigo));

    }

    @PostMapping
    public ResponseEntity criar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok().body(this.pessoaService.criar(pessoa));


    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{codigo}")
    public ResponseEntity deletar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok().body(this.pessoaService.deletar(pessoa));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{codigo}")
    public ResponseEntity alterar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok().body(this.pessoaService.alterar(pessoa));
    }

}
