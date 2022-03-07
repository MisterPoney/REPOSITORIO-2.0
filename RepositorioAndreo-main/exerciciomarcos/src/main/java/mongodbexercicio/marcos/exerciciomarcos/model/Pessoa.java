package mongodbexercicio.marcos.exerciciomarcos.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

@Data
@Document
public class Pessoa{
    @Id
    private String codigo;
    @javax.validation.constraints.NotBlank(message = "Não pode ter valor vazio")
    @Indexed(unique = true)
    private String nome;
    @javax.validation.constraints.NotBlank(message = "Não pode ter valor vazio")
    @NumberFormat
    @Range(min=0,max=120,message = "A idade não pode ser maior que 120 anos")
    private String idade;
    @javax.validation.constraints.NotBlank(message = "Não pode ter valor vazio")
    //@Pattern(regexp ="[^0-9]")
    private String telefone;


}