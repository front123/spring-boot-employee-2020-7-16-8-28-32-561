package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CompanyIntegrationTest extends CommonIntegrationTest{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void clearRepository() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    private Company addOneCompanyToDB(){
        Company company = new Company(1,"oocl");
        return companyRepository.save(company);
    }
    private Employee addEmployeeToDB(Company companyInDB){
        Employee employee = new Employee();
        employee.setCompany(companyInDB);
        employee.setId(1);
        employee.setName("jay");
        employee.setAge(18);
        employee.setGender("male");
        return employeeRepository.save(employee);
    }

    @Test
    void should_return_ok_when_get_all_companies_given_a_company_in_db() throws Exception {
        //given
        addOneCompanyToDB();
        //when and then
        mockMvc.perform(get("/companies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"company_id\":1,\"name\":\"oocl\"}]"));
    }

}
