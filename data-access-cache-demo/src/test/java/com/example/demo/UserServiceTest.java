package com.example.demo;

import com.example.demo.DataAccessCacheApplication;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DataAccessCacheApplication.class)
@ActiveProfiles("test")
class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    private User testUser;
    
    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setAge(25);
    }
    
    @Test
    void testCreateUser() {
        User createdUser = userService.createUser(testUser);
        
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals("testuser", createdUser.getUsername());
        assertEquals("test@example.com", createdUser.getEmail());
        assertEquals(25, createdUser.getAge());
    }
    
    @Test
    void testGetUserById() {
        User createdUser = userService.createUser(testUser);
        
        User foundUser = userService.getUserById(createdUser.getId());
        
        assertNotNull(foundUser);
        assertEquals(createdUser.getId(), foundUser.getId());
        assertEquals("testuser", foundUser.getUsername());
    }
    
    @Test
    void testUpdateUser() {
        User createdUser = userService.createUser(testUser);
        
        User updateUser = new User();
        updateUser.setUsername("updateduser");
        updateUser.setEmail("updated@example.com");
        updateUser.setAge(30);
        
        User updatedUser = userService.updateUser(createdUser.getId(), updateUser);
        
        assertNotNull(updatedUser);
        assertEquals("updateduser", updatedUser.getUsername());
        assertEquals("updated@example.com", updatedUser.getEmail());
        assertEquals(30, updatedUser.getAge());
    }
    
    @Test
    void testDeleteUser() {
        User createdUser = userService.createUser(testUser);
        Long userId = createdUser.getId();
        
        userService.deleteUser(userId);
        
        assertFalse(userRepository.existsById(userId));
    }
    
    @Test
    void testFindAllUsers() {
        userService.createUser(testUser);
        
        User user2 = new User();
        user2.setUsername("testuser2");
        user2.setEmail("test2@example.com");
        user2.setAge(28);
        userService.createUser(user2);
        
        List<User> users = userService.findAll();
        
        assertNotNull(users);
        assertEquals(2, users.size());
    }
    
    @Test
    void testFindByUsername() {
        userService.createUser(testUser);
        
        User foundUser = userService.findByUsername("testuser");
        
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }
    
    @Test
    void testExistsByUsername() {
        userService.createUser(testUser);
        
        assertTrue(userService.existsByUsername("testuser"));
        assertFalse(userService.existsByUsername("nonexistent"));
    }
    
    @Test
    void testGetUserByIdNotFound() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(999L);
        });
        
        assertEquals("用户不存在", exception.getMessage());
    }
}