package com.refrralApp.refrral.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public class CandidateRagisterRequest {
    @NotBlank(message = "please provide name")
    private String name;
    @NotBlank
    @Email(message = "email must be valid")
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min = 6 ,message = "password must be ta least 6 character ")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
