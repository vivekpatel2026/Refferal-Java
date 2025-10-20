package com.refrralApp.refrral.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CompanyRequest {
    @NotBlank
    private String name;
    private String logo;
    private String website;

    public CompanyRequest(String name) {
        this.name = name;
    }

    public CompanyRequest(String name, String logo, String website) {
        this.name = name;
        this.logo = logo;
        this.website = website;
    }

    public CompanyRequest() {
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
