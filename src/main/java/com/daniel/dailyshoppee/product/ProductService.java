package com.daniel.dailyshoppee.product;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {

    private static List<Product> products = new ArrayList<Product>();

    static{
        products.add(new Product(1,"Iphone 13 pro max",120000,"test image","test desc"));
        products.add(new Product(1,"Beats  studio wireless",25000,"test image","test desc"));
        products.add(new Product(1,"Samsung galaxy s22 ultra",140000,"test image","test desc"));
    }


    public List<Product> getAll(){
        return products;
    }
}
