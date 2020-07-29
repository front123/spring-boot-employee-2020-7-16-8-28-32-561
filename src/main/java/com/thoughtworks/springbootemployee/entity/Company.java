package com.thoughtworks.springbootemployee.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int company_id;

    @OneToMany
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
}