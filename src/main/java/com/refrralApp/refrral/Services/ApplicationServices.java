package com.refrralApp.refrral.Services;

import com.refrralApp.refrral.dto.request.CreateApplicationRequest;
import com.refrralApp.refrral.entity.Application;
import com.refrralApp.refrral.entity.Candidate;
import com.refrralApp.refrral.entity.Company;
import com.refrralApp.refrral.repository.ApplicationRespository;
import com.refrralApp.refrral.repository.CandidateRepository;
import com.refrralApp.refrral.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ApplicationServices {

    @Autowired
    private ApplicationRespository applicationRespository;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private  CandidateRepository candidateRepository;

    public Application createApplication(CreateApplicationRequest application,String email,Long candidateId,Long companyId){
        Optional<Candidate> optionalCandidate=candidateRepository.findByIdAndEmail(candidateId,email);
        if(!optionalCandidate.isPresent()){
            throw new RuntimeException("candidate not exist");
        }
        Candidate  candidate=optionalCandidate.get();
        Optional<Company > optionalCompany=companyRepository.findById(companyId);
        if(!optionalCompany.isPresent()){
            throw  new RuntimeException("company not Exist");
        }
        Company company=optionalCompany.get();
        application.setCandidate(candidate);
        application.setCompany(company);
        Application newApplication=new Application(application);
        return applicationRespository.save(newApplication);
    }


    public List<Application> getAllApplication(String email, long candidateId) {
        Optional<Candidate> optionalCandidate = candidateRepository.findByIdAndEmail(candidateId, email);
        if (!optionalCandidate.isPresent()) {
            throw new RuntimeException("Candidate does not exist");
        }

        List<Application> applications = applicationRespository.findByCandidateId(candidateId);
        return applications;
    }
}
