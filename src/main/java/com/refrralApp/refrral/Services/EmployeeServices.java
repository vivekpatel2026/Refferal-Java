package com.refrralApp.refrral.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refrralApp.refrral.dto.EmployeeGetProfileDTO;
import com.refrralApp.refrral.dto.EmployeeRagisetrRequest;
import com.refrralApp.refrral.entity.Company;
import com.refrralApp.refrral.entity.Employee;
import com.refrralApp.refrral.repository.CompanyRepository;
import com.refrralApp.refrral.repository.EmployeeRepository;
import com.refrralApp.refrral.utility.Helper;
import jakarta.el.ELManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServices {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private Helper helper;


    public EmployeeGetProfileDTO getEmployeeProfile(String email,Long employeeID){

        Optional<Employee> employee=employeeRepository.findByIdAndEmail(employeeID,email);
        if(!employee.isPresent()) {
            throw new RuntimeException("Employee not Exist");
        }
        return new EmployeeGetProfileDTO(employee.get());

    }


    public EmployeeGetProfileDTO updateProfile(JsonNode updates, String email, Long employeeID) throws JsonProcessingException {
        Optional<Employee> employeeOpt = employeeRepository.findByIdAndEmail(employeeID, email);
        if (!employeeOpt.isPresent()) {
            throw new RuntimeException("Employee does not exist");
        }

        Employee employee = employeeOpt.get();

        if (updates.has("name")) {
            employee.setName(updates.get("name").asText());
        }


        if (updates.has("position")) {
            employee.setPosition(updates.get("position").asText());
        }
        if(updates.has("company")) {
            String companyStr = updates.get("company").asText();
            if (companyStr != null && !companyStr.isEmpty()) {
                Long companyId = Long.parseLong(companyStr);
                Company company = companyRepository.findById(companyId)
                        .orElseThrow(() -> new RuntimeException("Company not found"));
                company.addEmployee(employee);
                employee.setCompany(company);
           }
        }

        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeGetProfileDTO(updatedEmployee);
    }
}
