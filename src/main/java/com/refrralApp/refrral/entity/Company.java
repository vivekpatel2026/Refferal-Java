package com.refrralApp.refrral.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.refrralApp.refrral.dto.CompanyRequest;
import com.refrralApp.refrral.repository.CompanyRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Marker;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String logo;
    private String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Application> applications;




    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public Company(CompanyRequest company) {
        this.name=company.getName();
        this.logo=company.getLogo();
        this.website=company.getWebsite();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    public void addEmployee(Employee employee){
        if(!this.employees.contains(employee)){
            this.employees.add(employee);
            employee.setCompany(this);
        }
    }
    public void removeEmployee(Employee employee){
        if(this.employees.contains(employee)){
            this.employees.remove(employee);
            employee.setCompany(null);
        }
    }

    public Company() {
    }

    public List<Application> getApplications() {
        return applications;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
