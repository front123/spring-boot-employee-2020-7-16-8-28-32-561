package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyByID(@PathVariable Integer id) throws CompanyNotFoundException {
        return companyService.getCompanyByID(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getAllEmployeesByCompanyId(@PathVariable Integer id){
        return companyService.getAllEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Company> getCompaniesByPaging(@RequestParam Integer page, @RequestParam Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return companyService.getCompaniesByPaging(pageable);
    }

    @PostMapping()
    public Company addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Integer id, @RequestBody Company company){
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeesByCompanyId(@PathVariable Integer id) throws CompanyNotFoundException {
        companyService.deleteEmployeesByCompanyId(id);
    }
}