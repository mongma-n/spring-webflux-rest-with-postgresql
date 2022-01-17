package me.mongma.webfluxdemo.emp.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class EmployeeHandler {

    public Mono<ServerResponse> fireEmployee(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> modifyEmployee(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getOneByName(ServerRequest serverRequest) {
        return null;
    }
    public Mono<ServerResponse> getOne(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> addEmployee(ServerRequest serverRequest) {
        return null;
    }
    public <T extends ServerResponse> Mono<T> getAll(ServerRequest serverRequest) {
        return null;
    }


}
