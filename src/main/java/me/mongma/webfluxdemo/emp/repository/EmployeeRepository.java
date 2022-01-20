package me.mongma.webfluxdemo.emp.repository;

import me.mongma.webfluxdemo.emp.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends R2dbcRepository<Employee, Long> {
    @Query("{ 'name': ?0 }")
    Flux<Employee> findByName(final String name);
}