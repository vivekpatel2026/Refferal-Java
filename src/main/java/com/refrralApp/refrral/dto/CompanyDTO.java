package com.refrralApp.refrral.dto;

import com.refrralApp.refrral.entity.Company;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class CompanyDTO {


    private Long id;
    private String name;
    private String logo;
    private String website;

    public CompanyDTO(Company company) {
        this.id=company.getId();
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
}
