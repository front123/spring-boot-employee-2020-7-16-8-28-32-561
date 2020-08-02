package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CompanyIntegrationTest extends CommonIntegrationTest{

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

    @Test
    void should_return_ok_when_delete_employees_by_company_id_given_a_company_in_db() throws Exception {
        //given
        Company companyInDB = addOneCompanyToDB();

        //when and then
        mockMvc.perform(delete("/companies/"+companyInDB.getId())).andExpect(status().isOk());
    }

    @Test
    void should_return_length_is_2_when_get_companies_by_paging_given_10_companies_in_db_and_page_is2_and_pageSize_is4() throws Exception {
        //given
        for(int i=0; i<10; i++){
            addOneCompanyToDB();
        }
        //when and then
        mockMvc.perform(get("/companies?page=2&pageSize=4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content.length()").value(2));
    }
}
