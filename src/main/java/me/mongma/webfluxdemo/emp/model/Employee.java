package me.mongma.webfluxdemo.emp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


@Data
@Table
public class Employee implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long seq;
    String name;
    int salary;
    String department;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
