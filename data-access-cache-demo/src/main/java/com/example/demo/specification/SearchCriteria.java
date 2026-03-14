package com.example.demo.specification;

import lombok.Data;

@Data
public class SearchCriteria {
    private String username;
    private String email;
    private Integer minAge;
    private Integer maxAge;
}