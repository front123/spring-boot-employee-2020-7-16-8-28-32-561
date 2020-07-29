package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import com.thoughtworks.springbootemployee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void should_size_equal_2_when_get_all_employees_given_2_employees() {
        //given
        List<Employee> employeesTemp = new ArrayList<>();
        for(int i=0; i<2; i++){
            Employee employee = new Employee();
            employee.setId(1);
            employeesTemp.add(employee);
        }
        Mockito.when(employeeRepository.findAll()).thenReturn(employeesTemp);
        //when
        List<Employee> employees = employeeService.getAllEmployees();
        //then
        Assertions.assertEquals(2, employees.size());
    }
}
