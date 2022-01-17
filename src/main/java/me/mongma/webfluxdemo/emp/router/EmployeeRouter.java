package me.mongma.webfluxdemo.emp.router;

import me.mongma.webfluxdemo.emp.handler.EmployeeHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class EmployeeRouter {
 
    public RouterFunction<ServerResponse> routes(EmployeeHandler handler) {
        return route(GET("/").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(GET("/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getOne)
                .andRoute(GET("name/{name}").and(accept(MediaType.APPLICATION_JSON)), handler::getOneByName)
                .andRoute(POST("/add").and(accept(MediaType.APPLICATION_JSON)), handler::addEmployee)
                .andRoute(PUT("/modify").and(accept(MediaType.APPLICATION_JSON)), handler::modifyEmployee)
                .andRoute(DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::fireEmployee);
    }
}
