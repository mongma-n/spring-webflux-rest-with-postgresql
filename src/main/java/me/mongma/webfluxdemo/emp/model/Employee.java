package me.mongma.webfluxdemo.emp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column("name")
    private String name;

    @Column("salary")
    private int salary;

    @Column("department")
    private String department;



}
