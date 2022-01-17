package me.mongma.webfluxdemo.emp.router;

import me.mongma.webfluxdemo.emp.handler.EmployeeHandler;
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
        return route(GET("/"), handler::getAll)
                .andRoute(GET("/{id}"), handler::getOne)
                .andRoute(GET("name/{name}"), handler::getOneByName)
                .andRoute(POST("/add"), handler::addEmployee)
                .andRoute(PUT("/modify"), handler::modifyEmployee)
                .andRoute(DELETE("/delete/{id}"), handler::fireEmployee);
    }
}
