package com.refrralApp.refrral.repository;

import com.refrralApp.refrral.entity.Application;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ApplicationRespository extends JpaRepository<Application,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Application a WHERE a.createdAt < :cutoffDate")
    void deleteOldApplications(@Param("cutoffDate") LocalDateTime cutoffDate);
}
