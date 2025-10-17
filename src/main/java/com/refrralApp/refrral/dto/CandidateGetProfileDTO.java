package com.refrralApp.refrral.dto;

import com.refrralApp.refrral.entity.Candidate;
import java.time.LocalDateTime;

public class CandidateGetProfileDTO {
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String collegeName;
    private String collegeCity;
    private String collegeState;
    private String github;
    private String portfolio;
    private String resumeUrl;
    private Integer graduationYear;
    private Double cgpa;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;


    public CandidateGetProfileDTO(Candidate candidate) {
        this.id = candidate.getId();
        this.name = candidate.getName();
        this.email = candidate.getEmail();
        this.phone = candidate.getPhone();
        this.collegeName = candidate.getCollegeName();
        this.collegeCity = candidate.getCollegeCity();
        this.collegeState = candidate.getCollegeState();
        this.github = candidate.getGithub();
        this.portfolio = candidate.getPortfolio();
        this.resumeUrl = candidate.getResumeUrl();
        this.graduationYear = candidate.getGraduationYear();
        this.cgpa = candidate.getCgpa();
        this.createdAt = candidate.getCreatedAt();
        this.updatedAt = candidate.getUpdatedAt();
    }
    // Getters (required if you use Spring Boot + Jackson for JSON serialization)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCollegeName() { return collegeName; }
    public String getCollegeCity() { return collegeCity; }
    public String getCollegeState() { return collegeState; }
    public String getGithub() { return github; }
    public String getPortfolio() { return portfolio; }
    public String getResumeUrl() { return resumeUrl; }
    public Integer getGraduationYear() { return graduationYear; }
    public Double getCgpa() { return cgpa; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
