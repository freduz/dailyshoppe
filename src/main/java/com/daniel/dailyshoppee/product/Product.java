package com.daniel.dailyshoppee.product;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Product name must not be blank")
    @Size(min = 5,message = "Product name must be 5 char long")
    private String name;

    @NotBlank(message = "Product price must not be blank")
    private String price;


    private String image;

    @NotBlank(message = "Description must not be blank")
    @Size(min = 6,message = "Description should be 6 char length")
    private String description;


}
