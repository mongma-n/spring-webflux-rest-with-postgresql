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

import static org.springframework.web.reactive.function.server.ServerResponse.*;

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
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(employees, Employee.class)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ServerResponse> addEmployee(ServerRequest req) {
        Mono<Employee> employee = req.bodyToMono(Employee.class);
        return employee
                .flatMap(emp -> repo.save(emp))
                .flatMap(emp -> created(URI.create("/v1/employee/" + emp.getSeq())).build());
    }

    public Mono<ServerResponse> modifyEmployee(ServerRequest req) {
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
                .flatMap(emp -> repo.save(emp))
                .flatMap(emp -> created(URI.create("/v1/employee/" + emp.getSeq())).build());
    }

    public Mono<ServerResponse> fireEmployee(ServerRequest req) {
        return ok().body((repo.deleteById(Long.valueOf(req.pathVariable("id")))), Employee.class);
    }

}
