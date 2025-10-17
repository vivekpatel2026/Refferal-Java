package com.refrralApp.refrral.controller;

import com.refrralApp.refrral.Services.CandidateServices;
import com.refrralApp.refrral.dto.CandidateGetProfileDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    private CandidateServices candidateServices;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request){
        String email = (String) request.getAttribute("email");
        String candidateId=(String) request.getAttribute("userId");
        CandidateGetProfileDTO candidate=candidateServices.getProfile(email,Long.parseLong(candidateId));
        return ResponseEntity.ok(Map.of("message", "profile fetch successfully", "Status","success","data",candidate));

    }

}
