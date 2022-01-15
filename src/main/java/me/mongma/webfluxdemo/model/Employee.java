package me.mongma.webfluxdemo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Employee {

    @Id
    @GeneratedValue
    int id;
    String name;
    long salary;
 
    //Getters and setters
 
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
}
