package com.example.demo.mongo.service;

import com.example.demo.mongo.entity.Product;
import com.example.demo.mongo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品不存在"));
    }
    
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    
    public List<Product> findByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
    
    public List<Product> findLowStockProducts() {
        return productRepository.findByStockGreaterThan(10);
    }
    
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
    
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    public Product updateProduct(String id, Product product) {
        Product existingProduct = findById(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setStock(product.getStock());
            existingProduct.setDescription(product.getDescription());
            return productRepository.save(existingProduct);
        }
        return null;
    }
    
    public List<Product> aggregateByCategory() {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.group("category").count().as("count"),
            Aggregation.sort(org.springframework.data.domain.Sort.Direction.DESC, "count")
        );
        
        AggregationResults<Product> results = mongoTemplate.aggregate(
            aggregation, "products", Product.class
        );
        
        return results.getMappedResults();
    }
}