package com.api.springboot.repositories;


import com.api.springboot.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//extend JpaRepository - assim habilitamos muitos métodos, e passamos o model e o tipo de identificador
@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

}
