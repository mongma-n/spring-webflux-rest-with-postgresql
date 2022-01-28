package me.mongma.webfluxdemo.emp.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@Table("employee")
public class Employee {

    @Id
    @Column("seq")
    long seq;

    @Column("name")
    String name;

    @Column("salary")
    int salary;

    @Column("department")
    String department;



}
