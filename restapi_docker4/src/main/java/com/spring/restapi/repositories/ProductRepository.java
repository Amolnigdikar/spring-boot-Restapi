package com.spring.restapi.repositories;

import com.spring.restapi.models.Product;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, Serializable> {
    
   
}