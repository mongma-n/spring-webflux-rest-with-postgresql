package me.mongma.webfluxdemo.router;

import me.mongma.webfluxdemo.model.Employee;
import me.mongma.webfluxdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class EmployeeRouter {
 
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/"), this::getAll)
                .andRoute(GET("/{id}"), this::getOne)
                .andRoute(GET("name/{name}"), this::getOneByName)
                .andRoute(POST("/add"), this::addEmployee)
                .andRoute(PUT("/modify"), this::modifyEmployee)
                .andRoute(DELETE("/delete/{id}"), this::fireEmployee);
    }

    private Mono<ServerResponse> fireEmployee(ServerRequest serverRequest) {
        return null;
    }

    private Mono<ServerResponse> modifyEmployee(ServerRequest serverRequest) {
        return null;
    }

    private Mono<ServerResponse> getOneByName(ServerRequest serverRequest) {
        return null;

    }

    private Mono<ServerResponse> getOne(ServerRequest serverRequest) {
        return null;
    }

    private Mono<ServerResponse> addEmployee(ServerRequest serverRequest) {
        return null;
    }

    private <T extends ServerResponse> Mono<T> getAll(ServerRequest serverRequest) {
        return null;
    }


}
