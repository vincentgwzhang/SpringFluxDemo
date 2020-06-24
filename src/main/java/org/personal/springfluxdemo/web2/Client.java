package org.personal.springfluxdemo.web2;

import java.util.List;
import org.personal.springfluxdemo.web1.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

public class Client
{
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://127.0.0.1:11223");

        String id = "1";
        User user = webClient.get().uri("/user/{id}", id).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(User.class).block();
        System.out.println(user);

        System.out.println("==============================================================================================================");

        Flux<User> userFlux = webClient.get().uri("/user").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(User.class);
        //userFlux.buffer().doOnNext(System.out::println).blockFirst();
        List<User> userList = userFlux.buffer().blockFirst();
        userList.forEach(System.out::println);
    }
}
