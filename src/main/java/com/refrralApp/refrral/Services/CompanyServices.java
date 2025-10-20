package com.refrralApp.refrral.Services;

import com.refrralApp.refrral.dto.CompanyDTO;
import com.refrralApp.refrral.dto.request.CompanyRequest;
import com.refrralApp.refrral.entity.Company;
import com.refrralApp.refrral.entity.Employee;
import com.refrralApp.refrral.repository.CompanyRepository;
import com.refrralApp.refrral.repository.EmployeeRepository;
import com.refrralApp.refrral.utility.CompanyIdNameProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompanyServices {


    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public CompanyDTO createProfile(CompanyRequest companyRequest,String email,Long employeeId){
        Optional<Employee> employee=employeeRepository.findByIdAndEmail(employeeId,email);
        if(!employee.isPresent()){
            throw  new RuntimeException("You have no permission to create company");
        }
        Company company=new Company(companyRequest);
        company=companyRepository.save(company);
        CompanyDTO createCompany=new CompanyDTO(company);
        return createCompany;
    }

    public List<CompanyIdNameProjection> getAllCompany(){
        return companyRepository.findAllCompanyIdAndName();
    }


}
