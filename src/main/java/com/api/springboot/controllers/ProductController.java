package com.api.springboot.controllers;

import com.api.springboot.dtos.ProductRecordDto;
import com.api.springboot.models.ProductModel;
import com.api.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository; //é um ponto de injeção, para termos acesso a todos os metodos JPA

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct (@RequestBody @Valid ProductRecordDto productRecordDto) {
    //metodo do tipo productModel, com nome saveProduct, que vai receber como corpo da solicitação, o productRecordDto (que já tem os dados mapeados)
        var productModel = new ProductModel(); //aqui iniciamos o moodel, pq é o que de fato vamos salvar na base de dados
        BeanUtils.copyProperties(productRecordDto, productModel); //convertendo de dto para model usando essa biblioteca do spring
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel)); //configurando a resposta ao cliente
    }

}
