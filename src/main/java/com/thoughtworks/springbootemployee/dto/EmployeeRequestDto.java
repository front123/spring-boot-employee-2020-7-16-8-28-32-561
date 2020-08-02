package com.thoughtworks.springbootemployee.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeRequestDto {
    @NotNull
    @Size(min = 1, max = 100)
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private int age;
    @Size(min = 1,max = 10)
    @NotBlank
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

    public EmployeeRequestDto(String name, int age, String gender, int companyId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.companyId = companyId;
    }

    public EmployeeRequestDto() {
    }
}
