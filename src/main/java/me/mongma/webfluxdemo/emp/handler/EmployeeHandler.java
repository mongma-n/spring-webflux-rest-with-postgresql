package me.mongma.webfluxdemo.emp.handler;

import me.mongma.webfluxdemo.emp.model.Employee;
import me.mongma.webfluxdemo.emp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class EmployeeHandler {

    @Autowired
    private EmployeeRepository repo;

    public Mono<ServerResponse> getOne(ServerRequest req) {
        return repo.findById(Long.valueOf(req.pathVariable("id")))
                .flatMap(emp -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(emp))
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ServerResponse> getOneByName(ServerRequest req) {
        Flux<Employee> employees = repo.findByName(req.pathVariable("name"));
        return ok().contentType(MediaType.APPLICATION_JSON).body(employees, Employee.class)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ServerResponse> getAll(ServerRequest req) {
        Flux<Employee> employees = repo.findAll();
        return ok().contentType(MediaType.APPLICATION_JSON).body(employees, Employee.class)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ServerResponse> addEmployee(ServerRequest req) {
        Mono<Employee> employee = req.bodyToMono(Employee.class);
        return repo.saveAll(employee)
                .flatMap(emp -> created(URI.create("/v1/employee/")).build())
                .next();
    }

    public Mono<ServerResponse> modifyEmployee(ServerRequest req) {
        return null;
    }

    public Mono<ServerResponse> fireEmployee(ServerRequest req) {
        return ok().body(repo.deleteById(Long.valueOf(req.pathVariable("id"))), Employee.class);
    }

}
