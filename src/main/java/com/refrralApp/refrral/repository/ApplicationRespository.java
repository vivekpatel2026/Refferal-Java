package com.refrralApp.refrral.repository;

import com.refrralApp.refrral.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRespository extends JpaRepository<Application,Long> {
}
