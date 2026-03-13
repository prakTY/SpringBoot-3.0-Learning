package com.example.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api/i18n")
public class I18nController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/welcome")
    public String getWelcomeMessage(@RequestParam String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        return messageSource.getMessage("welcome.message", null, locale);
    }

    @GetMapping("/user/{id}")
    public String getUserMessage(@PathVariable Long id, @RequestParam String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        return messageSource.getMessage("user.notfound", null, locale);
    }
}