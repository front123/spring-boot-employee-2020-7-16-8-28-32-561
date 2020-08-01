package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
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
//    private final CompanyService companyService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
//        this.companyService = companyService;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        return employee;
    }

    @Override
    public EmployeeResponseDto updateEmployee(Integer employeeId, EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException {

        Optional<Company> company = companyRepository.findById(employeeRequestDto.getCompanyId());
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto, employee);
        employee.setCompany(company.get());
        employee.setId(employeeId);
        Employee employeeReturn = employeeRepository.save(employee);

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(employeeReturn, employeeResponseDto);
        employeeResponseDto.setCompanyName(company.get().getName());
        return employeeResponseDto;
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
    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException {

        Optional<Company> company = companyRepository.findById(employeeRequestDto.getCompanyId());
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto, employee);
        employee.setCompany(company.get());
        Employee employeeReturn = employeeRepository.save(employee);
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(employeeReturn, employeeResponseDto);
        employeeResponseDto.setCompanyName(company.get().getName());
        return employeeResponseDto;
    }


}
