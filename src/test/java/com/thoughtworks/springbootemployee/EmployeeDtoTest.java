package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeDtoTest {
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void should_name_equal_when_add_employee_by_employee_dto_given_employee_request() throws CompanyNotFoundException {
        //given
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto("xiaoming",18,"male",1);

        Company company = new Company(1,"oocl");
        when(companyRepository.findById(1)).thenReturn(Optional.of(company));
        when(employeeRepository.save(any())).thenReturn(new Employee(1,"xiaoming",123,"male"));
        //when
        EmployeeResponseDto addEmployee = employeeService.addEmployee(employeeRequestDto);

        //then
        Assertions.assertEquals(employeeRequestDto.getName(), addEmployee.getName());

    }

}
