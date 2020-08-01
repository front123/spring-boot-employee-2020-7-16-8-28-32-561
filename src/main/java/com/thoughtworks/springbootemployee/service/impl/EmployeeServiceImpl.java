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

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
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
    public EmployeeResponseDto updateEmployee(Integer employeeId, EmployeeRequestDto employeeRequestDto) throws CompanyNotFoundException {

        Company company = companyRepository.findById(employeeRequestDto.getCompanyId()).orElseThrow(CompanyNotFoundException::new);
        return createResponse(company, employeeRequestDto);
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

        Company company = companyRepository.findById(employeeRequestDto.getCompanyId()).orElseThrow(CompanyNotFoundException::new);
        return createResponse(company, employeeRequestDto);
    }

    private EmployeeResponseDto createResponse(Company company, EmployeeRequestDto employeeRequestDto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto, employee);
        employee.setCompany(company);
        Employee employeeReturn = employeeRepository.save(employee);
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(employeeReturn, employeeResponseDto);
        employeeResponseDto.setCompanyName(company.getName());
        return employeeResponseDto;
    }
}
