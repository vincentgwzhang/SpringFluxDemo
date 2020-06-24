package org.personal.springfluxdemo.web2.handler;

import org.personal.springfluxdemo.web1.entity.User;
import org.personal.springfluxdemo.web1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

public class UserHandler
{

    private UserService userService;

    public UserHandler(UserService userService)
    {
        this.userService = userService;
    }

    public Mono<ServerResponse> getUserByID(ServerRequest request)
    {
        final Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        final Integer id = Integer.parseInt(request.pathVariable("id"));
        final Mono<User> userMono = this.userService.getUserById(id);
        return userMono.flatMap(_user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(_user))).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request)
    {
        final Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        final Flux<User> userFlux = this.userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userFlux, User.class).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> saveUser(ServerRequest request)
    {
        final Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        final Mono<User> userMono = request.bodyToMono(User.class);

        return ServerResponse.ok().build(this.userService.saveUserInfo(userMono)).switchIfEmpty(notFound);
    }

}
