package com.example.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/webflux")
public class WebFluxController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello WebFlux!");
    }

    @GetMapping("/users")
    public Flux<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "张三", "zhangsan@example.com"));
        users.add(new User(2L, "李四", "lisi@example.com"));
        users.add(new User(3L, "王五", "wangwu@example.com"));
        return Flux.fromIterable(users);
    }

    @GetMapping("/users/{id}")
    public Mono<User> getUserById(@PathVariable Long id) {
        return Mono.just(new User(id, "用户" + id, "user" + id + "@example.com"));
    }

    @GetMapping("/stream")
    public Flux<String> streamData() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "数据流: " + sequence)
                .take(10);
    }

    @GetMapping("/numbers")
    public Flux<Integer> generateNumbers() {
        return Flux.range(1, 10)
                .map(n -> n * 2);
    }

    public static class User {
        private Long id;
        private String name;
        private String email;

        public User(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
    }
}