package com.refrralApp.refrral.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refrralApp.refrral.Services.EmployeeServices;
import com.refrralApp.refrral.dto.EmployeeGetProfileDTO;
import com.refrralApp.refrral.entity.Employee;
import com.refrralApp.refrral.utility.JwtUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private  EmployeeServices employeeServices;

    @GetMapping("/profile")
    private ResponseEntity<?> employeeProfile(HttpServletRequest request){
        String email = (String) request.getAttribute("email");
        String EmployeeId=(String) request.getAttribute("userId");
        EmployeeGetProfileDTO employee=employeeServices.getEmployeeProfile(email,Long.parseLong(EmployeeId));
        return ResponseEntity.ok(Map.of("message", "Employee profile fetch successfully", "status"," success","data",employee));
    }
    @PutMapping("/update-profile")

    public ResponseEntity<?> updateProfile(HttpServletRequest request,@RequestBody JsonNode updates)throws JsonProcessingException {
        String email = (String) request.getAttribute("email");
        String EmployeeId=(String) request.getAttribute("userId");

        EmployeeGetProfileDTO employee=employeeServices.updateProfile(updates,email,Long.parseLong(EmployeeId));


        return ResponseEntity.ok(Map.of("message", "Employee profile update successfully", "status"," success","data",employee));



    }
}
