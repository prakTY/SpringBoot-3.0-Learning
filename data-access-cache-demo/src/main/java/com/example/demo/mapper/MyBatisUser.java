package com.example.demo.mybatis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBatisUser {
    private Long id;
    private String username;
    private String email;
    private Integer age;
}