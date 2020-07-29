package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping( params = {"gender"})
    public List<Employee> getEmployeesByGender(@RequestParam String gender){
        return employeeService.getEmployeesByGender(gender);
    }

    @GetMapping( params = {"page", "pageSize"})
    public Page<Employee> getEmployeesByPaging(@RequestParam Integer page, @RequestParam Integer pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        return employeeService.getEmployeesByPaging(pageable);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }
}
