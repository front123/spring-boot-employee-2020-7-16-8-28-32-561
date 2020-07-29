package com.thoughtworks.springbootemployee.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int company_id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int id) {
        this.company_id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}