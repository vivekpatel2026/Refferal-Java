package com.refrralApp.refrral.Services;

import com.refrralApp.refrral.dto.CandidateGetProfileDTO;
import com.refrralApp.refrral.entity.Candidate;
import com.refrralApp.refrral.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateServices {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateGetProfileDTO getProfile(String email,Long candidateId){

        Optional<Candidate> candidate=candidateRepository.findByIdAndEmail(candidateId,email);
        if(!candidate.isPresent()){
            throw new RuntimeException("candidate not exist");
        }
        return new CandidateGetProfileDTO(candidate.get());
    }
}
