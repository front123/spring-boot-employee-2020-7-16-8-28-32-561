package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void should_size_equal_2_when_get_all_employees_given_2_employees() {
        //given
        List<Employee> employeesTemp = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
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

    @Test
    void should_return_employee_id_1_when_get_employee_by_id_given_2_employees_id_1_and_2() throws EmployeeNotFoundException {
        //given
        Employee employee = new Employee();
        employee.setId(1);
        Mockito.when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(employee));
        //when
        Employee actualEmployee = employeeService.getEmployeeById(1);
        //then
        Assertions.assertEquals(1, actualEmployee.getId());
    }

    @Test
    void should_return_1_employee_with_id_1_when_update_employee_given_1_employee_with_id_1() throws CompanyNotFoundException {
        //given
        Employee employee = new Employee();
        employee.setId(1);
        Company company = new Company();
        Mockito.when(companyRepository.findById(Mockito.any())).thenReturn(Optional.of(company));
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
        //when
        EmployeeResponseDto actualEmployee = employeeService.updateEmployee(1, employeeRequestDto);
        //then
        Assertions.assertEquals(employee.getId(), actualEmployee.getId());
    }

    /*
    given
        2 male and 1 female employees and gender = male
    when
        findByGender
    then
        return a list size = 2
     */
    @Test
    void should_return_2_employees_when_get_employees_by_gender_given_2_male_and_1_female_employees_and_gender_is_male() {
        //given
        String gender = "male";
        List<Employee> maleAndFemaleEmployees = new ArrayList<>();
        List<Employee> maleEmployees = new ArrayList<>();
        for(int i=0; i<2; i++){
            Employee employee = new Employee();
            employee.setId(i);
            employee.setGender("male");
            maleEmployees.add(employee);
            maleAndFemaleEmployees.add(employee);
        }
        Employee femaleEmployee = new Employee();
        femaleEmployee.setId(3);
        femaleEmployee.setGender("female");
        maleAndFemaleEmployees.add(femaleEmployee);
        Mockito.when(employeeRepository.findByGender(gender)).thenReturn(maleEmployees);
         //when
        List<Employee> employees = employeeService.getEmployeesByGender(gender);

        //then
        Assertions.assertEquals(2, employees.size());
    }
}
