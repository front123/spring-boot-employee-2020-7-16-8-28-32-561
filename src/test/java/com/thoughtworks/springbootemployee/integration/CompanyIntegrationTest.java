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
        employee.setName("jay");
        employee.setAge(18);
        employee.setGender("male");
        return employeeRepository.save(employee);
    }

    @Test
    void should_return_ok_when_get_all_companies_given_a_company_in_db() throws Exception {
        //given
        Company companyInDB = addOneCompanyToDB();
        //when and then
        mockMvc.perform(get("/companies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":"+companyInDB.getId()+",\"name\":\"oocl\"}]"));
    }

    @Test
    void should_return_a_company_with_name_oocl_when_get_company_by_id_given_a_company_in_db() throws Exception {
        //given
        Company companyInDB = addOneCompanyToDB();

        //when and then
        mockMvc.perform(get("/companies/"+companyInDB.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("oocl"));

    }

    @Test
    void should_return_2_employees_when_get_all_employees_by_company_id_given_a_company_with_2_employees_in_db() throws Exception {
        //given
        Company companyInDB = addOneCompanyToDB();
        Employee employee1 = addEmployeeToDB(companyInDB);
        Employee employee2 = addEmployeeToDB(companyInDB);

        //when and then
        mockMvc.perform(get("/companies/"+companyInDB.getId()+"/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("jay"))
                .andExpect(jsonPath("[1].name").value("jay"));
    }

    @Test
    void should_return_a_company_with_name_oocl_when_add_company_given_a_company_json() throws Exception {
        String newCompanyJsonStr = "{\"id\":1, \"name\":\"oocl\"}";

        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCompanyJsonStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("oocl"));

    }

    @Test
    void should_return_company_with_name_tw_when_update_company_given_a_company_with_name_oocl_in_db() throws Exception {
        //given
        Company companyInDB = addOneCompanyToDB();
        String newCompanyJsonStr = "{\"id\":"+companyInDB.getId()+", \"name\":\"tw\"}";

        //when and then
        mockMvc.perform(put("/companies/"+companyInDB.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCompanyJsonStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("tw"));
    }

}
