package com.daniel.dailyshoppee.product;


import com.daniel.dailyshoppee.exception.ProductNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRespository productRespository;

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<List<Product>>(this.productRespository.findAll(),HttpStatus.OK);
    }

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = productRespository.findById(id).orElse(null);
        if(product == null){
            throw new ProductNotFound("There is no product associated with this ID: "+id);
        }
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int id){
        productRespository.deleteById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestBody Product product){
        Product updatedProduct = productRespository.save(product);
        return  new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);
    }

    @PostMapping(path = "/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct = productRespository.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
