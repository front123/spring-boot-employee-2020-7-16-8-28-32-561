package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class EmployeeIntegrationTest extends CommonIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void clearRepository() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_return_ok_when_get_all_employees_given_0_employees() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }

    @Test
    void should_return_ok_when_get_employee_by_id_given_id_1() throws Exception {
        //given
        Company company = new Company(1,"oocl");
        Company companyInDB = companyRepository.save(company);
        Employee employee = new Employee();
        employee.setCompany(companyInDB);
        employee.setId(1);
        employee.setName("jay");
        employee.setAge(18);
        employee.setGender("male");
        Employee employeeInDB = employeeRepository.save(employee);

        //when and then
        mockMvc.perform(get("/employees/"+employeeInDB.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("jay"));
    }

    @Test
    void should_return_ok_when_add_employee_given_a_company_and_a_employee() throws Exception, CompanyNotFoundException {
        //given
        Company company = new Company();
        company.setName("oocl");
        Company companyInDB = companyRepository.save(company);
        //when and then
        String employeeJsonStr = "{\n" + "\"name\":\"Jay\",\n" + "\"age\": 15,\n"
                + "\"gender\": \"male\",\n"
                + "\"companyId\":"+companyInDB.getCompany_id()+"\n" + "}";
        mockMvc.perform(post("/employees")
                .content(employeeJsonStr).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("Jay"));
    }

    @Test
    void should_return_ok_when_delete_a_employee_given_add_a_employee_and_delete_it() throws Exception {
        //given
        Company company = new Company(1,"oocl");
        Company companyInDB = companyRepository.save(company);
        Employee employee = new Employee();
        employee.setCompany(companyInDB);
        employee.setId(1);
        employee.setName("jay");
        employee.setAge(18);
        employee.setGender("male");
        Employee employeeInDB = employeeRepository.save(employee);
        //when and then
        mockMvc.perform(delete("/employees/"+employeeInDB.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_ok_when_modify_a_employee_given_a_employee() throws Exception {
        //given
        Company company = new Company(1,"oocl");
        Company companyInDB = companyRepository.save(company);
        Employee employee = new Employee();
        employee.setCompany(companyInDB);
        employee.setId(1);
        employee.setName("jay");
        employee.setAge(18);
        employee.setGender("male");
        Employee employeeInDB = employeeRepository.save(employee);
        String modifyEmployeeJsonStr = "{\n" + "\"name\":\"chengcheng\",\n" + "\"age\": 15,\n"
                + "\"gender\": \"Female\",\n" + "\"companyId\":"
                +companyInDB.getCompany_id()
                +"\n" + "}";
        //when and then
        mockMvc.perform(put("/employees/" + employeeInDB.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(modifyEmployeeJsonStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("chengcheng"));
    }

    @Test
    void should_return_2_when_paged_employee_list_given_page_2_and_page_size_4() throws Exception {
        //given
        Company company = new Company(1,"oocl");
        Company companyInDB = companyRepository.save(company);
        for(int i=0; i<10; i++){
            Employee employee = new Employee();
            employee.setCompany(companyInDB);
            employee.setName("jay"+i);
            employee.setAge(18);
            employee.setGender("male");
            employeeRepository.save(employee);
        }
        //when and then
        String url = "/employees?page=2&pageSize=4";
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content[0].name").value("jay8"))
                .andExpect(jsonPath("content[1].name").value("jay9"));
    }
}
