package me.mongma.webfluxdemo.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.mongma.webfluxdemo.emp.model.Employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("{ 'name': ?0 }")
    Flux<Employee> findByName(final String name);
}