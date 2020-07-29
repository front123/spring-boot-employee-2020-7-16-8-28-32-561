package com.thoughtworks.springbootemployee.entity;


import java.util.List;

public class Company {
    private int company_id;

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