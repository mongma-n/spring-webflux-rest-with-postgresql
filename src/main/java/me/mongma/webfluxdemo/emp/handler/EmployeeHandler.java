package me.mongma.webfluxdemo.emp.handler;

import me.mongma.webfluxdemo.emp.model.Employee;
import me.mongma.webfluxdemo.emp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class EmployeeHandler {
    @Autowired
    private EmployeeRepository repo;

    public Mono<ServerResponse> getOne(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getOneByName(ServerRequest serverRequest) {
        return null;
    }

    public <T extends ServerResponse> Mono<T> getAll(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> addEmployee(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> modifyEmployee(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> fireEmployee(ServerRequest serverRequest) {
        return null;
    }

}
