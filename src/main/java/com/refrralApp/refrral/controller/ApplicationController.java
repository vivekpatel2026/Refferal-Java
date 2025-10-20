package com.refrralApp.refrral.controller;

import com.refrralApp.refrral.Services.ApplicationServices;
import com.refrralApp.refrral.dto.request.CreateApplicationRequest;
import com.refrralApp.refrral.entity.Application;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    @Autowired
    public ApplicationServices applicationServices;

    @PostMapping("/create/{companyId}")
    public ResponseEntity<?> createApplication(HttpServletRequest request, @RequestBody CreateApplicationRequest application,@PathVariable Long companyId){
        String email = (String) request.getAttribute("email");
        String candidateId=(String) request.getAttribute("userId");
        Application newApplication =applicationServices.createApplication(application,email,Long.parseLong(candidateId),companyId);
        return ResponseEntity.ok(Map.of("status","success","message","application created successfull","data",newApplication));
    }

    @GetMapping("/all-application")
    public ResponseEntity<?> getAllApplication(HttpServletRequest request){
        String email = (String) request.getAttribute("email");
        String candidateId=(String) request.getAttribute("userId");
        List<Application> allApplication=applicationServices.getAllApplication(email,Long.parseLong(candidateId));
        return ResponseEntity.ok(Map.of("status","success","message","candidate all application","data",allApplication));
    }


}
