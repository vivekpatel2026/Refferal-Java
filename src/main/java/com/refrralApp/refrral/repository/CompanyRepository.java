package com.refrralApp.refrral.repository;

import com.refrralApp.refrral.entity.Company;
import com.refrralApp.refrral.utility.CompanyIdNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Query("SELECT c.id AS id, c.name AS name FROM Company c")
    List<CompanyIdNameProjection> findAllCompanyIdAndName();


}
