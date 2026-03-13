package com.example.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Thymeleaf 示例页面");
        model.addAttribute("username", "访客");
        model.addAttribute("now", LocalDateTime.now());
        
        model.addAttribute("user", new User("张三", "zhangsan@example.com"));
        
        List<User> people = new ArrayList<>();
        people.add(new User("张三", 25, true));
        people.add(new User("李四", 30, false));
        people.add(new User("王五", 28, true));
        people.add(new User("赵六", 35, false));
        model.addAttribute("people", people);
        
        return "index";
    }

    public static class User {
        private String name;
        private String email;
        private Integer age;
        private Boolean active;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public User(String name, Integer age, Boolean active) {
            this.name = name;
            this.age = age;
            this.active = active;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }
    }
}