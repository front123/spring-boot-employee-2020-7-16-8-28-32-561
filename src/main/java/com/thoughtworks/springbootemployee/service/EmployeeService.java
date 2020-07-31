package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id) throws EmployeeNotFoundException;

    EmployeeResponse updateEmployee(Integer id,EmployeeRequest employeeRequest) throws CompanyNotFoundException;

    void deleteEmployeeById(int id);

    List<Employee> getEmployeesByGender(String gender);

    Page<Employee> getEmployeesByPaging(Pageable pageable);

    EmployeeResponse addEmployee(EmployeeRequest employeeRequest) throws CompanyNotFoundException;
}
