package com.jashan.queue_forge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jashan.queue_forge.Repository.TechnicianRepository;
import com.jashan.queue_forge.enums.TechnicianStatus;
import com.jashan.queue_forge.models.Technicians;
import com.jashan.queue_forge.worker.TechnicianWorker;
import com.jashan.queue_forge.worker.WorkshopConfig;

@Service 
public class WorkshopService {

    private final WorkshopConfig workshopConfig;
    private final TechnicianRepository technicianRepository;

    WorkshopService(WorkshopConfig workshopConfig,
                    TechnicianRepository technicianRepository
    ){
        this.workshopConfig=workshopConfig;
        this.technicianRepository=technicianRepository;
    }

    public void startWorkshop(){

        List<Technicians> technicians= technicianRepository.findByTechnicianStatus(TechnicianStatus.AVAILABLE);

        for(Technicians technician : technicians){
            TechnicianWorker worker = new TechnicianWorker(technician, technician.getTechnicianId(),this);
            workshopConfig.workshopExecutor().submit(worker);
        }
    }
    

}
