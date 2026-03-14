package com.example.demo.specification;

import com.example.demo.entity.User;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaQuery;

public class UserSpecification implements Specification<User> {
    
    private final SearchCriteria criteria;
    
    public UserSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }
    
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        
        if (criteria.getUsername() != null && !criteria.getUsername().isEmpty()) {
            predicate = cb.and(predicate, 
                cb.like(root.get("username"), "%" + criteria.getUsername() + "%"));
        }
        
        if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
            predicate = cb.and(predicate, 
                cb.like(root.get("email"), "%" + criteria.getEmail() + "%"));
        }
        
        if (criteria.getMinAge() != null) {
            predicate = cb.and(predicate, 
                cb.ge(root.get("age"), criteria.getMinAge()));
        }
        
        if (criteria.getMaxAge() != null) {
            predicate = cb.and(predicate, 
                cb.le(root.get("age"), criteria.getMaxAge()));
        }
        
        return predicate;
    }
}