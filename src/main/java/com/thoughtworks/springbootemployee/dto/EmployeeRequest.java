package com.thoughtworks.springbootemployee.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeRequest {
    @NotNull
    private String name;
    @NotNull
    private int age;
    @Size(min = 1,max = 10)
    private String gender;
    @NotNull
    private int companyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public EmployeeRequest(String name, int age, String gender, int companyId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.companyId = companyId;
    }

    public EmployeeRequest() {
    }
}
