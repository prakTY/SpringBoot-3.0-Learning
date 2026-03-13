package com.example.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}