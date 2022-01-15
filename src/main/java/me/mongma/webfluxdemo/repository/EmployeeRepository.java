package me.mongma.webfluxdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.mongma.webfluxdemo.model.Employee;

import org.springframework.data.jpa.repository.Query;
import reactor.core.publisher.Flux;
 
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("{ 'name': ?0 }")
    Flux<Employee> findByName(final String name);
}