package com.refrralApp.refrral.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refrralApp.refrral.dto.CandidateGetProfileDTO;
import com.refrralApp.refrral.entity.Candidate;
import com.refrralApp.refrral.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CandidateServices {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public CandidateGetProfileDTO getProfile(String email,Long candidateId){

        Optional<Candidate> candidate=candidateRepository.findByIdAndEmail(candidateId,email);
        if(!candidate.isPresent()){
            throw new RuntimeException("candidate not exist");
        }
        return new CandidateGetProfileDTO(candidate.get());
    }

    public CandidateGetProfileDTO updateProfile(JsonNode updates, String email, long candidateId) throws IOException {
        Optional<Candidate> optionalCandidate=candidateRepository.findByIdAndEmail(candidateId,email);
        if(!optionalCandidate.isPresent()){
            throw new RuntimeException("candidate not exist");
        }
        Candidate  candidate=optionalCandidate.get();


        // Apply updates from JSON to existing object
        candidate = objectMapper.readerForUpdating(candidate).readValue(updates);

        // Save updated candidate
        candidateRepository.save(candidate);

        // Convert to DTO (assuming you have a mapper method)
        return new CandidateGetProfileDTO(candidate);


    }
}
