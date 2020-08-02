package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public EmployeeResponseDto addEmployee(@RequestBody @Valid EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException {
        return employeeService.addEmployee(employeeRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Integer id) {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeesByGender(@RequestParam String gender) {
        return employeeService.getEmployeesByGender(gender);
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Employee> getEmployeesByPaging(@RequestParam Integer page, @RequestParam Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return employeeService.getEmployeesByPaging(pageable);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable Integer id, @RequestBody @Valid EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException {
        return employeeService.updateEmployee(id, employeeRequestDto);
    }
}
