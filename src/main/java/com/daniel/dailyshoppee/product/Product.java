package com.daniel.dailyshoppee.product;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product_tbl")
public class Product {

    @Id
    @GeneratedValue()
    private Integer id;
    private String name;
    private Integer price;
    private String image;
    private String description;


}
