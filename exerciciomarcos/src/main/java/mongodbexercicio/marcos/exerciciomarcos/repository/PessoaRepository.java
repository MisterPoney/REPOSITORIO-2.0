package mongodbexercicio.marcos.exerciciomarcos.repository;

import mongodbexercicio.marcos.exerciciomarcos.model.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {

}
