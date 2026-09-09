package com.jashan.queue_forge.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class WorkshopConfig {

    @Bean 
    public ExecutorService workshopExecutor(){
        
        return Executors.newFixedThreadPool(4);
        
    }

}
