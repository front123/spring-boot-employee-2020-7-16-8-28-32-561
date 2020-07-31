package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
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

    @AfterEach
    void clearRepository() {
        employeeRepository.deleteAll();
    }

    void addACompanyOOCl() throws Exception {
        String companyJsonStr = "{\"name\":\"oocl\"}";
        mockMvc.perform(post("/companies").content(companyJsonStr).contentType(MediaType.APPLICATION_JSON));
    }

    void addAEmployeeJay() throws Exception {
        String employeeJsonStr = "{\n" + "\"name\":\"Jay\",\n" + "\"age\": 15,\n" + "\"gender\": \"male\",\n" + "\"companyId\":1\n" + "}";
        mockMvc.perform(post("/employees").content(employeeJsonStr).contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void should_return_ok_when_get_all_employees_given_0_employees() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }

    @Test
    void should_return_ok_when_get_employee_by_id_given_id_1() throws Exception {
        //given
        addACompanyOOCl();
        addAEmployeeJay();
        int employeeId = 1;

        //when and then
        mockMvc.perform(get("/employees/" + Integer.toString(employeeId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Jay"));
    }

    @Test
    void should_return_ok_when_add_employee_given_a_company_and_a_employee() throws Exception, CompanyNotFoundException {
        //given
        addACompanyOOCl();
        //when and then
        String employeeJsonStr = "{\n" + "\"name\":\"Jay\",\n" + "\"age\": 15,\n" + "\"gender\": \"male\",\n" + "\"companyId\":1\n" + "}";
        mockMvc.perform(post("/employees")
                .content(employeeJsonStr).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("Jay"));

    }

    @Test
    void should_return_ok_when_delete_a_employee_given_add_a_employee_and_delete_it() throws Exception {
        //given
        addACompanyOOCl();
        addAEmployeeJay();
        int employeeId = 1;
        //when and then
        mockMvc.perform(delete("/employees/" + Integer.toString(employeeId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_ok_when_modify_a_employee_given_a_employee() throws Exception {
        //given
        addACompanyOOCl();
        addAEmployeeJay();
        int employeeId = 1;
        String modifyEmployeeJsonStr = "{\n" + "\"name\":\"chengcheng\",\n" + "\"age\": 15,\n" + "\"gender\": \"Female\",\n" + "\"companyId\":1\n" + "}";
        //when and then
        mockMvc.perform(put("/employees/" + Integer.toString(employeeId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(modifyEmployeeJsonStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("chengcheng"));

    }

    @Test
    void should_return_2_when_paged_employee_list_given_page_2_and_page_size_4() throws Exception {
        //given
        addACompanyOOCl();
        for (int i = 0; i < 10; i++) {
            addAEmployeeJay();
        }
        //when and then
        String url = "/employees/?page=2&pageSize=4";
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content[0].id").value(9))
                .andExpect(jsonPath("content[1].id").value(10))
                .andExpect(jsonPath("content[2].id").doesNotExist());

    }


}
