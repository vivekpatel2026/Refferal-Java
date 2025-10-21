package com.refrralApp.refrral.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.refrralApp.refrral.dto.request.EmployeeRagisetrRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String name;
    private String position;
    @NotBlank
    @Email(message = "Email must be valid")
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 6,message = "password must be min 6 character ")
    @JsonIgnore
    private String password;
    @ManyToOne
    @JoinColumn(name = "company_id",nullable = true)
    @JsonIgnoreProperties({"applications", "otherFieldYouWantToHide"})
    private Company company;
    private Boolean isVerified = false;
    // Referrals made by employee
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeReferral> referrals;

    // Applications viewed by employee
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeViewedApplication> viewedApplications;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Employee() {
    }

    public Employee(EmployeeRagisetrRequest employee) {
        this.email=employee.getEmail();
        this.password=employee.getPassword();
        this.name=employee.getName();
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        // Remove from old company
        if (this.company != null && this.company.getEmployees() != null) {
            this.company.getEmployees().remove(this);
        }
        this.company = company;
        if (company != null && !company.getEmployees().contains(this)) {
            company.getEmployees().add(this);
        }

    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public List<EmployeeReferral> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<EmployeeReferral> referrals) {
        this.referrals = referrals;
    }

    public List<EmployeeViewedApplication> getViewedApplications() {
        return viewedApplications;
    }

    public void setViewedApplications(List<EmployeeViewedApplication> viewedApplications) {
        this.viewedApplications = viewedApplications;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }




}
