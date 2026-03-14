package com.example.demo;

import com.example.demo.DataAccessCacheApplication;
import com.example.demo.entity.User;
import com.example.demo.mongo.entity.Product;
import com.example.demo.mongo.service.ProductService;
import com.example.demo.redis.service.RedisCacheService;
import com.example.demo.service.UserService;
import com.example.demo.specification.SearchCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DataAccessCacheApplication.class)
@ActiveProfiles("test")
class IntegrationTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private RedisCacheService redisCacheService;
    
    @BeforeEach
    void setUp() {
        userService.deleteAll();
    }
    
    @Test
    void testUserCreationAndRetrieval() {
        User user = new User();
        user.setUsername("integrationtest");
        user.setEmail("integration@example.com");
        user.setAge(30);
        
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        
        User retrievedUser = userService.getUserById(createdUser.getId());
        assertEquals(createdUser.getUsername(), retrievedUser.getUsername());
        assertEquals(createdUser.getEmail(), retrievedUser.getEmail());
    }
    
    @Test
    void testDynamicQuery() {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setAge(25);
        userService.createUser(user1);
        
        User user2 = new User();
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setAge(35);
        userService.createUser(user2);
        
        SearchCriteria criteria = new SearchCriteria();
        criteria.setUsername("user");
        
        Page<User> result = userService.searchUsers(criteria, PageRequest.of(0, 10));
        
        assertNotNull(result);
        assertTrue(result.getContent().size() >= 2);
    }
    
    @Test
    void testMongoDBProductOperations() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(99.99);
        product.setCategory("Electronics");
        product.setStock(100);
        product.setDescription("Integration test product");
        
        Product createdProduct = productService.createProduct(product);
        assertNotNull(createdProduct);
        assertNotNull(createdProduct.getId());
        
        Product retrievedProduct = productService.findById(createdProduct.getId());
        assertEquals(createdProduct.getName(), retrievedProduct.getName());
        assertEquals(createdProduct.getPrice(), retrievedProduct.getPrice());
    }
    
    @Test
    void testMongoDBCategoryQuery() {
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(50.0);
        product1.setCategory("Books");
        product1.setStock(50);
        productService.createProduct(product1);
        
        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(75.0);
        product2.setCategory("Books");
        product2.setStock(30);
        productService.createProduct(product2);
        
        List<Product> books = productService.findByCategory("Books");
        
        assertNotNull(books);
        assertEquals(2, books.size());
        assertTrue(books.stream().allMatch(p -> "Books".equals(p.getCategory())));
    }
    
    @Test
    void testRedisCacheOperations() {
        String testKey = "test:cache:key";
        String testValue = "cached value";
        
        redisCacheService.set(testKey, testValue);
        
        Object retrievedValue = redisCacheService.get(testKey);
        
        assertEquals(testValue, retrievedValue);
        assertTrue(redisCacheService.exists(testKey));
        
        redisCacheService.delete(testKey);
        assertFalse(redisCacheService.exists(testKey));
    }
    
    @Test
    void testPriceRangeQuery() {
        Product product1 = new Product();
        product1.setName("Cheap Product");
        product1.setPrice(25.0);
        product1.setCategory("Test");
        product1.setStock(100);
        productService.createProduct(product1);
        
        Product product2 = new Product();
        product2.setName("Expensive Product");
        product2.setPrice(150.0);
        product2.setCategory("Test");
        product2.setStock(50);
        productService.createProduct(product2);
        
        Product product3 = new Product();
        product3.setName("Medium Product");
        product3.setPrice(75.0);
        product3.setCategory("Test");
        product3.setStock(75);
        productService.createProduct(product3);
        
        List<Product> products = productService.findByPriceRange(30.0, 100.0);
        
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Medium Product", products.get(0).getName());
    }
    
    @Test
    void testLowStockProducts() {
        Product product1 = new Product();
        product1.setName("High Stock Product");
        product1.setPrice(50.0);
        product1.setCategory("Test");
        product1.setStock(100);
        productService.createProduct(product1);
        
        Product product2 = new Product();
        product2.setName("Low Stock Product");
        product2.setPrice(75.0);
        product2.setCategory("Test");
        product2.setStock(5);
        productService.createProduct(product2);
        
        List<Product> lowStockProducts = productService.findLowStockProducts();
        
        assertNotNull(lowStockProducts);
        assertTrue(lowStockProducts.stream().allMatch(p -> p.getStock() <= 10));
    }
}