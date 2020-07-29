package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompanyByID(Integer id) throws CompanyNotFoundException;

    List<Employee> getAllEmployeesByCompanyId(Integer id);

    Page<Company> getCompaniesByPaging(Pageable pageable);

    Company addCompany(Company company);

    Company updateCompany(Company company);

    void deleteCompanyById(Integer id);
}
