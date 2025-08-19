package com.api.springboot.controllers;

import com.api.springboot.dtos.ProductRecordDto;
import com.api.springboot.models.ProductModel;
import com.api.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {
    //neste caso estamos usando o repository junto com o controller
    //o ideal é sempre utilizar a service para intermediar essas outras 2 camadas

    @Autowired
    ProductRepository productRepository; //é um ponto de injeção, para termos acesso a todos os metodos JPA

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct (@RequestBody @Valid ProductRecordDto productRecordDto) {
    //metodo do tipo productModel, com nome saveProduct, que vai receber como corpo da solicitação, o productRecordDto (que já tem os dados mapeados)
        var productModel = new ProductModel(); //aqui iniciamos o moodel, pq é o que de fato vamos salvar na base de dados
        BeanUtils.copyProperties(productRecordDto, productModel); //convertendo de dto para model usando essa biblioteca do spring
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel)); //configurando a resposta ao cliente
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){  //o retorno vai ser um responseEntity, mas uma lista de produtos
        //acrescentando o hateoas, q vai retornar um campo a mais, no caso o link com detalhes do produto (getById)
        List<ProductModel> productList = productRepository.findAll();
        if(!productList.isEmpty()) { //se a lista nn estiver vazia, vamos passar pelo for, para montar o link de cada produto
            for (ProductModel product : productList) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
                //linkTo - Para qual endpoint foi redirecionar o cliente
                //methodOn - qual é o controller que está o metodo, e qual o metodo em si
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productList); //findAll, save são métodos do JPA
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){ //definido o tipo de retorno como object, pq podemos ter
        //2 tipos de respostas
        Optional<ProductModel> product0 = productRepository.findById(id); //findById é mais um metodo JPA
        if(product0.isEmpty()) { //isEmpty é um metodo do Optional
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        product0.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if(product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = product0.get(); //atribuindo ao objeto, o valor que já recebemos da base de dados (linha 48), pq o objeto já existe
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> product0 = productRepository.findById(id);
        if(product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.delete(product0.get()); //delete é comando do próprio JPA
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfuly");
    }
}
