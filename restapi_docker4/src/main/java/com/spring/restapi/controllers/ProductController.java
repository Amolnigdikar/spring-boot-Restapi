package com.spring.restapi.controllers;


import com.spring.restapi.models.*;
import com.spring.restapi.repositories.OrderRepository;
import com.spring.restapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(method=RequestMethod.GET, value="/products")
    public Iterable<Product> product() {
        return productRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/create_product")
    public String save(@RequestBody Product product) {
        productRepository.save(product);

        return product.getId();
    }

    @RequestMapping(method=RequestMethod.GET, value="/product/{id}")
    public Product show(@PathVariable String id) {
        return productRepository.findOne(id);
        
    }

    @RequestMapping(method=RequestMethod.PUT, value="/update_product/{id}")
    public Product update(@PathVariable String id, @RequestBody Product product) {
        Product prod = productRepository.findOne(id);
        if(product.getProdName() != null)
            prod.setProdName(product.getProdName());
        if(product.getProdDesc() != null)
            prod.setProdDesc(product.getProdDesc());
        
            prod.setProdPrice(product.getProdPrice());
        if(product.getProdImage() != null)
            prod.setProdImage(product.getProdImage());
        productRepository.save(prod);
        return prod;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/delete_product/{id}")
    public String delete(@PathVariable String id) {
        Product product = productRepository.findOne(id);
        productRepository.delete(product);

        return "product deleted";
        
    }
    
   
    
}
