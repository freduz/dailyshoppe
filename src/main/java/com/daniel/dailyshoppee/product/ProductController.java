package com.daniel.dailyshoppee.product;


import com.daniel.dailyshoppee.exception.ProductNotFound;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRespository productRespository;

    @GetMapping(path = "/products")
    public ResponseEntity<MappingJacksonValue> getAllProducts(){
        List<Product> products = this.productRespository.findAll();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(products);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("product_name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ProductBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);
        return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue,HttpStatus.OK);
    }

    @GetMapping(path = "/products/{id}")
    public EntityModel<Product> getProduct(@PathVariable int id){
        Product product = productRespository.findById(id).orElse(null);
        if(product == null){
            throw new ProductNotFound("There is no product associated with this ID: "+id);
        }
        EntityModel<Product> entityModel = EntityModel.of(product);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts());
//        return new ResponseEntity<Product>(product,HttpStatus.OK);
        entityModel.add(link.withRel("all-products"));
        return entityModel;
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
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Product savedProduct = productRespository.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
