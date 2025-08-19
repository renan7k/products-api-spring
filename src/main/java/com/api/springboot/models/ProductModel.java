package com.api.springboot.models;

import jakarta.persistence.*;


import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel implements Serializable { //indica que é uma classe que pode passar por serializações
    @Serial
    private static final long serialVersionUID = 1L;

    @Id //identifica o atributo que será o identificador
    @GeneratedValue(strategy = GenerationType.AUTO)  //como será gerado o identificador
    private UUID idProduct;  //UUID é um tipo universal, e geralmente usado em sistemas distribuídos
    private String name;
    private BigDecimal value;

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
