package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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



}
