package com.example.demo.mongo.repository;

import com.example.demo.mongo.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    
    Product findByName(String name);
    
    List<Product> findByCategory(String category);
    
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    
    List<Product> findByStockGreaterThan(Integer stock);
}