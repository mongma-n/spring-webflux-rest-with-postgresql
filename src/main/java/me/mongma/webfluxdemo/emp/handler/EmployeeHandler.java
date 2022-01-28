package me.mongma.webfluxdemo.emp.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mongma.webfluxdemo.emp.model.Employee;
import me.mongma.webfluxdemo.emp.repository.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeHandler {

    private final EmployeeRepository repo;

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
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(employees, Employee.class)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ServerResponse> addEmployee(ServerRequest req) {
        Mono<Employee> employee = req.bodyToMono(Employee.class);
        return employee
                .flatMap(repo::save)
                .flatMap(emp -> created(URI.create("/v1/employee/" + emp.getSeq())).build());
    }

    @Transactional
    public Mono<ServerResponse> modifyEmployee(ServerRequest req) {
        log.info("modifyEmployee invoked.");
        return Mono.zip(
                    (data) -> {
                        Employee emp1 = (Employee) data[0];
                        Employee emp2 = (Employee) data[1];
                        emp1.setName(emp2.getName());
                        emp1.setSalary(emp2.getSalary());
                        emp1.setDepartment(emp2.getDepartment());
                        return emp1;
                    },
                    repo.findById(Long.valueOf(req.pathVariable("id"))),
                    req.bodyToMono(Employee.class)
                )
                .cast(Employee.class)
                .flatMap(repo::save)
                .flatMap(emp -> created(URI.create("/v1/employee/" + emp.getSeq())).build());
    }

    public Mono<ServerResponse> fireEmployee(ServerRequest req) {
        return ok().body((repo.deleteById(Long.valueOf(req.pathVariable("id")))), Employee.class);
    }

}
