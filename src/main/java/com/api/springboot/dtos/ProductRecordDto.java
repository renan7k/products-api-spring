package com.api.springboot.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

//record são imutáveis, então uma vez criado, não é possível alterar os valores.
//por isso nn possui o metodo setter
//por padrão, os atributos são private e final

//neste caso, passamos os dados que serão enviado pelo cliente para ser salvo no db : nome e valor
//o id é gerado automaticamente, então não passamos

//dto serve apenas para receber o que virá no corpo da requisição json, serializar para o objeto Java
// e transformar em um model para ser salvo na base de dados
public record ProductRecordDto(@NotBlank String  name, @NotNull BigDecimal value) {
}
