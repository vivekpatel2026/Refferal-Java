package com.refrralApp.refrral.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refrralApp.refrral.Services.CompanyServices;
import com.refrralApp.refrral.dto.CompanyDTO;
import com.refrralApp.refrral.dto.CompanyRequest;
import com.refrralApp.refrral.utility.CompanyIdNameProjection;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyServices companyServices;


    @PostMapping("/create")
    public ResponseEntity<?>  createCompany( HttpServletRequest request,@RequestBody CompanyRequest createCom){

        String email = (String) request.getAttribute("email");
        String EmployeeId=(String) request.getAttribute("userId");
        CompanyDTO newCompany=companyServices.createProfile(createCom,email,Long.parseLong(EmployeeId));
        return ResponseEntity.ok(Map.of("message", "create company successfully", "status"," success","data",newCompany));
    }

    @GetMapping("public/getAll")
    public ResponseEntity<?>  getAllCompany(){
        List<CompanyIdNameProjection> allCompany=companyServices.getAllCompany();
        return ResponseEntity.ok(Map.of("message", "fetch All Company successfully", "status"," success","data",allCompany));
    }






}
