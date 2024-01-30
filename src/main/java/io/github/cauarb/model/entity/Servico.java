package io.github.cauarb.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @ManyToOne // muitos serviços para um cliente
    @JoinColumn(name = "id_cliente") //define uma chave estrageira para tebela clientes
    private Cliente cliente;

    @Column
    private BigDecimal valor;
}
