package com.refrralApp.refrral.utility;

import com.refrralApp.refrral.repository.ApplicationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

public class ApplicationCleanupJob {

    @Autowired
    private ApplicationRespository applicationRespository;

    // Runs every day at midnight (00:00)
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteOldApplications() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(30);
        applicationRespository.deleteOldApplications(cutoffDate);
        System.out.println("✅ Deleted applications older than 30 days: " + cutoffDate);
    }
}
