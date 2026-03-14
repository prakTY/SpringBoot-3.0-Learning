package com.example.demo.service;

import com.example.demo.mybatis.entity.MyBatisUser;
import com.example.demo.mapper.MyBatisUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBatisUserService {
    
    @Autowired
    private MyBatisUserMapper userMapper;
    
    public MyBatisUser findById(Long id) {
        return userMapper.findById(id);
    }
    
    public int insert(MyBatisUser user) {
        return userMapper.insert(user);
    }
    
    public int update(MyBatisUser user) {
        return userMapper.update(user);
    }
    
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }
    
    public List<MyBatisUser> findByUsernameLike(String username) {
        return userMapper.findByUsernameLike(username);
    }
}