package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CommonIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected EmployeeRepository employeeRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    @AfterEach
    protected void clearRepository() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    protected Company addOneCompanyToDB(){
        Company company = new Company();
        company.setName("oocl");
        return companyRepository.save(company);
    }
    protected Employee addEmployeeToDB(Company companyInDB){
        Employee employee = new Employee();
        employee.setCompany(companyInDB);
        employee.setName("jay");
        employee.setAge(18);
        employee.setGender("male");
        return employeeRepository.save(employee);
    }

}
