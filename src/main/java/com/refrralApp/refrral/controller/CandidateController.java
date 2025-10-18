package com.refrralApp.refrral.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.refrralApp.refrral.Services.CandidateServices;
import com.refrralApp.refrral.dto.CandidateGetProfileDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(HttpServletRequest request,@RequestBody JsonNode updates) throws IOException {
        String email = (String) request.getAttribute("email");
        String candidateId=(String) request.getAttribute("userId");
        CandidateGetProfileDTO updateCandidate=candidateServices.updateProfile(updates,email,Long.parseLong(candidateId));
        return ResponseEntity.ok(Map.of("status","success","message","candidate profile update successfully","data",updateCandidate));
    }

}
