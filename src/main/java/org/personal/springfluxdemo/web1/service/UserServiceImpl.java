package org.personal.springfluxdemo.web1.service;

import java.util.HashMap;
import java.util.Map;
import org.personal.springfluxdemo.web1.entity.User;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("userService")
public class UserServiceImpl implements UserService
{
    private final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        this.users.put(1, new User("a1", "M", 12));
        this.users.put(2, new User("a2", "F", 12));
        this.users.put(3, new User("a3", "M", 12));
        this.users.put(4, new User("a4", "F", 12));
        this.users.put(5, new User("a5", "M", 12));
    }

    @Override
    public Mono<User> getUserById(int id)
    {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<User> getAllUser()
    {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono)
    {
        return userMono.doOnNext(person -> {
            users.put(users.size()+1, person);
        }).then(Mono.empty());
    }
}
