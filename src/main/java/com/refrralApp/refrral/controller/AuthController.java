package com.refrralApp.refrral.controller;

import com.refrralApp.refrral.Services.AuthServices;
import com.refrralApp.refrral.dto.CandidateGetProfileDTO;
import com.refrralApp.refrral.dto.CandidateRagisterRequest;
import com.refrralApp.refrral.dto.EmployeeRagisetrRequest;
import com.refrralApp.refrral.entity.Candidate;
import com.refrralApp.refrral.entity.Employee;
import com.refrralApp.refrral.utility.JwtUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthServices authServices;
    // Candidate Registration
    @PostMapping("/candidate/register")
    public ResponseEntity<?> candidateRegister(@Valid @RequestBody CandidateRagisterRequest candidate){
        System.out.println(candidate.getEmail()+" "+candidate.getPassword());
        CandidateGetProfileDTO newCandidate= new CandidateGetProfileDTO(authServices.candidateRegister(candidate));
        Map<String ,Object> response=Map.of("Staus","Sucess","data",newCandidate);

        return ResponseEntity.ok(response);
    }
    // Candidate Login
    @GetMapping("/candidate/login")
    public  ResponseEntity<?> candidateLogin( @RequestBody Map<String, String> body){
        String email = body.get("email");
        String password = body.get("password");
        Candidate candidate=authServices.candidateLogin(email,password);
        if(candidate==null){
            Map<String ,Object> response=Map.of("Staus","fail","message","invalid credintial");
            return ResponseEntity.ok(response);

        }
        CandidateGetProfileDTO loginCandidate=new CandidateGetProfileDTO(candidate);
        Map<String ,Object> response=Map.of("Staus","Success","token",jwtUtility.generateToken(loginCandidate.getEmail()+" "+loginCandidate.getId()),"Candidate",loginCandidate);

        return ResponseEntity.ok(response);
    }
    // Employee Registration
    @PostMapping("/employee/register")
    public ResponseEntity<?> employeeRegister(@Valid @RequestBody EmployeeRagisetrRequest employee){
        Employee newEmployee=authServices.EmployeeRegister(employee);
        Map<String ,Object> response=Map.of("Status","Success","data",newEmployee);

        return ResponseEntity.ok(response);
    }

    //Employee log in
    @GetMapping("/employee/login")
    public  ResponseEntity<?> employeeLogin( @RequestBody Map<String, String> body){
        String email = body.get("email");
        String password = body.get("password");
        Employee employee=authServices.employeeLogin(email,password);
        if(employee==null){
            Map<String ,Object> response=Map.of("Status","failed","message","invalid credential");
            return ResponseEntity.ok(response);

        }
        Map<String ,Object> response=Map.of("Status","Success","token",jwtUtility.generateToken(employee.getEmail()+" "+employee.getId()),"employee",employee);

        return ResponseEntity.ok(response);
    }
}
