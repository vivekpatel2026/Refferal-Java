package com.refrralApp.refrral.dto.request;

import com.refrralApp.refrral.entity.Candidate;
import com.refrralApp.refrral.entity.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreateApplicationRequest {

    private Candidate candidate;


    private Company company;

    @NotBlank
    private String targetRole;

    @ElementCollection
    private List<String> projectLinks;

    private String videoPitch;
    private String customPitch;
    private String resume;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public List<String> getProjectLinks() {
        return projectLinks;
    }

    public void setProjectLinks(List<String> projectLinks) {
        this.projectLinks = projectLinks;
    }

    public String getVideoPitch() {
        return videoPitch;
    }

    public void setVideoPitch(String videoPitch) {
        this.videoPitch = videoPitch;
    }

    public String getCustomPitch() {
        return customPitch;
    }

    public void setCustomPitch(String customPitch) {
        this.customPitch = customPitch;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
