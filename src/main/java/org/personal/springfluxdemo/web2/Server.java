package org.personal.springfluxdemo.web2;

import org.personal.springfluxdemo.web1.service.UserServiceImpl;
import org.personal.springfluxdemo.web2.handler.UserHandler;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

public class Server
{
    // Create router
    public RouterFunction<ServerResponse> routingFunction()
    {
        UserHandler handler = new UserHandler(new UserServiceImpl());

        return RouterFunctions
                    .route(
                        GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        handler::getUserByID
                    )
                    .andRoute(
                        GET("/user").and(accept(MediaType.APPLICATION_JSON)),
                        handler::getAllUsers
                    )
                    .andRoute(
                        POST("/user").and(accept(MediaType.APPLICATION_JSON)),
                        handler::saveUser
                    );
    }

    public void createReactorServer()
    {
        // Create router (web path + real execution)
        RouterFunction<ServerResponse> route = routingFunction();

        // Create handler
        HttpHandler httpHandler = toHttpHandler(route);

        // Create adapter
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        // Create server
        HttpServer httpServer = HttpServer.create().port(11223);
        httpServer.handle(adapter).bindNow();
    }
}
