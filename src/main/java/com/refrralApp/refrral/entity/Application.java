package com.refrralApp.refrral.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotBlank
    private String targetRole;

    @ElementCollection
    private List<String> projectLinks;

    private String videoPitch;
    private String customPitch;
    private String resume;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeViewedApplication> viewedBy;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeReferral> referrals;

    @NotBlank
    private String status; // submitted / seen / referred / rejected

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<EmployeeViewedApplication> getViewedBy() {
        return viewedBy;
    }

    public void setViewedBy(List<EmployeeViewedApplication> viewedBy) {
        this.viewedBy = viewedBy;
    }

    public List<EmployeeReferral> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<EmployeeReferral> referrals) {
        this.referrals = referrals;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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