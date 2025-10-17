package com.refrralApp.refrral.Services;

import com.refrralApp.refrral.dto.CandidateRagisterRequest;
import com.refrralApp.refrral.dto.EmployeeRagisetrRequest;
import com.refrralApp.refrral.entity.Candidate;
import com.refrralApp.refrral.entity.Employee;
import com.refrralApp.refrral.repository.CandidateRepository;
import com.refrralApp.refrral.repository.EmployeeRepository;
import com.refrralApp.refrral.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServices {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JwtUtility jwtUtility;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Candidate candidateRegister(CandidateRagisterRequest candidate){
        candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
        Candidate newCandidate=new Candidate(candidate);
        return candidateRepository.save(newCandidate);
    }

    public  Candidate candidateLogin(String email,String password){
        Optional<?> candidate=candidateRepository.findByEmail(email);
        if(!candidate.isPresent()){
            return null;
        }
        Candidate existingCandidate= (Candidate) candidate.get();
        if(passwordEncoder.matches(password,existingCandidate.getPassword()))
            return existingCandidate;
        return null;
    }

    public Employee EmployeeRegister(EmployeeRagisetrRequest employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee newEmployee=new Employee(employee);
        return employeeRepository.save(newEmployee);
    }
    public  Employee employeeLogin(String email,String password){
        Optional<?> employee=employeeRepository.findByEmail(email);
        if(!employee.isPresent()){
            return null;
        }
        Employee existingEmployee= (Employee) employee.get();
        if(passwordEncoder.matches(password,existingEmployee.getPassword()))
            return existingEmployee;
        return null;
    }
}
