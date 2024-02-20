package com.javabootcamp.demofinnhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.service.CompanyService;

@Component
@EnableScheduling
public class SchedulerTaskConfig {

    public static boolean start = false;

    @Autowired
    private CompanyService companyService;

    @Scheduled(fixedRate = 60000) // 60s
    public void fixedRateTask() throws InterruptedException, FinnhubException {
        if (start) {
            companyService.refresh();
            
        }
    }
    
}
