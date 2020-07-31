package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public EmployeeResponse updateEmployee(Integer employeeId,EmployeeRequest employeeRequest) throws CompanyNotFoundException {

        Optional<Company> company = Optional.ofNullable(companyService.getCompanyByID(employeeRequest.getCompanyId()));
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        employee.setCompany(company.get());
        employee.setId(employeeId);
        Employee employeeReturn = employeeRepository.save(employee);

        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employeeReturn, employeeResponse);
        employeeResponse.setCompanyName(company.get().getName());
        return employeeResponse;
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    @Override
    public Page<Employee> getEmployeesByPaging(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) throws CompanyNotFoundException {

        Optional<Company> company = Optional.ofNullable(companyService.getCompanyByID(employeeRequest.getCompanyId()));
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        employee.setCompany(company.get());

        Employee employeeReturn = employeeRepository.save(employee);
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employeeReturn, employeeResponse);
        employeeResponse.setCompanyName(company.get().getName());
        return employeeResponse;
    }


}
