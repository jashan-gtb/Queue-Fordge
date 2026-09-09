package com.jashan.queue_forge.worker;

import com.jashan.queue_forge.enums.TechnicianStatus;
import com.jashan.queue_forge.models.RepairJob;
import com.jashan.queue_forge.models.Technicians;
import com.jashan.queue_forge.service.WorkshopService;

import lombok.AllArgsConstructor;



@AllArgsConstructor 
public class TechnicianWorker implements Runnable{
    
    private final Technicians technicians;
    private final WorkshopService service;

    @Override 
    public void run(){
        //ASSIGNING JOB
        while(technicians.getTechnicianStatus()== TechnicianStatus.AVAILABLE) {

            RepairJob repairjob= service.claimNextJob(technicians);
            //PROCESSING JOB
            if (repairjob==null) {
              break;  
            }  
            service.processJob(repairjob, technicians);
        }
    }

    

}
