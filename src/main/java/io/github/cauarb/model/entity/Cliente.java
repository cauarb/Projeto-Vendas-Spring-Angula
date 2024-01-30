package io.github.cauarb.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data // faztodo o serviço de getter e setter, criar construtor  com e sem paramentro e equals e hashcode
@NoArgsConstructor // manter construtor vazio
@AllArgsConstructor // gera um contrutor com todas as propriedades
@Builder // facilita na crianção de cliente
public class Cliente {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150) //nome fica vazio e obriga a inserção + tamanho de caractere
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy") // formatar data pra esse padrão
    private LocalDate dataCadastro;
    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDate.now());
    }

}
