package mongodbexercicio.marcos.exerciciomarcos.utlilitarios;

import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;

public class Utilitário {
    private void normalizeTelefone(Pessoa pessoa) {
         pessoa.setTelefone(pessoa.getTelefone().replaceAll("[^0-9]", ""));
    }
}
