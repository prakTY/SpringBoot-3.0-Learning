package com.example.demo.mongo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    
    @Id
    private String id;
    
    @Field("name")
    @Indexed
    private String name;
    
    @Field("price")
    private Double price;
    
    @Field("category")
    @Indexed
    private String category;
    
    @Field("stock")
    private Integer stock;
    
    @Field("description")
    private String description;
}