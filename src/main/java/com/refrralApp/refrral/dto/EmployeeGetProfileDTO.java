package com.refrralApp.refrral.dto;

import com.refrralApp.refrral.entity.Company;
import com.refrralApp.refrral.entity.Employee;
import com.refrralApp.refrral.entity.EmployeeReferral;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeGetProfileDTO {

    private Long id;
    private String name;
    private String position;
    private String email;
    private Company company;
    private Boolean isVerified;
    private List<EmployeeReferralDTO> referrals;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ✅ Constructor that maps Employee → DTO
    public EmployeeGetProfileDTO(Employee employee) {
        if (employee != null) {
            this.id = employee.getId();
            this.name = employee.getName();
            this.position = employee.getPosition();
            this.email = employee.getEmail();
            this.company = employee.getCompany();
            this.isVerified = employee.getVerified();
            this.createdAt = employee.getCreatedAt();
            this.updatedAt = employee.getUpdatedAt();

            // ✅ Deep copy referrals safely
            if (employee.getReferrals() != null) {
                this.referrals = employee.getReferrals().stream()
                        .map(EmployeeReferralDTO::new)
                        .collect(Collectors.toList());
            }
        }
    }

    // ✅ Nested DTO for referrals (based on your entity)
    public static class EmployeeReferralDTO {
        private Long id;
        private Long applicationId;

        private LocalDateTime referredAt;

        public EmployeeReferralDTO(EmployeeReferral referral) {
            if (referral != null) {
                this.id = referral.getId();

                if (referral.getApplication() != null) {
                    this.applicationId = referral.getApplication().getId();// Change if your Application has another field name
                }

                this.referredAt = referral.getReferredAt();
            }
        }

        public Long getId() {
            return id;
        }

        public Long getApplicationId() {
            return applicationId;
        }

        public LocalDateTime getReferredAt() {
            return referredAt;
        }
    }

    // === Getters ===
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public Company getCompany() {
        return company;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public List<EmployeeReferralDTO> getReferrals() {
        return referrals;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
