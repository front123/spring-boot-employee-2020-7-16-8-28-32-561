package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class EmployeeIntegrationTest extends CommonIntegrationTest{

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void clearRepository(){
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_ok_when_get_all_employees_given_0_employees() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }

    @Test
    void should_return_ok_when_get_employee_by_id_given_id_1() throws Exception{
        mockMvc.perform(get("/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("chengcheng"));
    }

    @Test
    void should_return_ok_when_add_employee_given_a_company_and_a_employee() throws Exception {
        String employeeJsonStr = "{\"id\":1,\"name\":\"chengcheng\",\"age\":54,\"gender\":\"male\",\"company\":{\"company_id\":1,\"name\":\"oocl\"}}";
        mockMvc.perform(post("/employees")
                .content(employeeJsonStr).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("chengcheng"));

    }
}
