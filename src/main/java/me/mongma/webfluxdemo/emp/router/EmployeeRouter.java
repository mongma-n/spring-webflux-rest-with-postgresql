package me.mongma.webfluxdemo.emp.router;

import lombok.RequiredArgsConstructor;
import me.mongma.webfluxdemo.emp.handler.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class EmployeeRouter {

    private final EmployeeHandler handler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route()
                .path("/v1/employee", builder -> builder
                    .nest(accept(MediaType.APPLICATION_JSON), builder1 -> builder1
                        .GET("/", handler::getAll)
                        .GET("/{id}", handler::getOne)
                        .GET("/name/{name}", handler::getOneByName)
                        .POST("/add", handler::addEmployee)
                        .PUT("/modify/{id}", handler::modifyEmployee)
                        .DELETE("/delete/{id}", handler::fireEmployee)
                    )
                ).build();
    }
}
