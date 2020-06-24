package org.personal.springfluxdemo.web1.controller;

import org.personal.springfluxdemo.web1.entity.User;
import org.personal.springfluxdemo.web1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public Mono<User> getUserByID(@PathVariable int id)
    {
        return this.userService.getUserById(id);
    }

    @GetMapping("user")
    public Flux<User> getAllUser()
    {
        return this.userService.getAllUser();
    }

    @PostMapping("/user")
    public Mono<Void> saveUser(@RequestBody User user)
    {
        return this.userService.saveUserInfo(Mono.just(user));
    }
}
