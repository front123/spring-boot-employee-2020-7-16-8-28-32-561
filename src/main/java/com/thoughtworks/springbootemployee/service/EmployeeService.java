package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id) throws EmployeeNotFoundException;

    Employee updateEmployee(Employee employee);

    Employee addEmployee(Employee employee);

    void deleteEmployeeById(int id);

    List<Employee> getEmployeesByGender(String gender);
}
