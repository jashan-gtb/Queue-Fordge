package com.jashan.queue_forge.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.jashan.queue_forge.Repository.RepairJobRepository;
import com.jashan.queue_forge.Repository.TechnicianRepository;
import com.jashan.queue_forge.enums.RepairStatus;
import com.jashan.queue_forge.enums.TechnicianStatus;
import com.jashan.queue_forge.models.RepairJob;
import com.jashan.queue_forge.models.Technicians;
import com.jashan.queue_forge.worker.TechnicianWorker;
import com.jashan.queue_forge.worker.WorkshopConfig;

@Service 
public class WorkshopService {

    private final WorkshopConfig workshopConfig;
    private final TechnicianRepository technicianRepository;
    private final RepairJobRepository repairJobRepository;

    WorkshopService(WorkshopConfig workshopConfig,
                    TechnicianRepository technicianRepository,
                    RepairJobRepository repairJobRepository
    ){
        this.workshopConfig=workshopConfig;
        this.technicianRepository=technicianRepository;
        this.repairJobRepository=repairJobRepository;
    }

    public void startWorkshop(){

        List<Technicians> technicians= technicianRepository.findByTechnicianStatus(TechnicianStatus.AVAILABLE);

        for(Technicians technician : technicians){
            TechnicianWorker worker = new TechnicianWorker(technician,this);
            workshopConfig.workshopExecutor().submit(worker);
        }
    }

    public RepairJob claimNextJob(Technicians technicians){

        List<RepairJob> repairJobs= repairJobRepository.findByRepairStatusOrderByPriorityDesc(RepairStatus.PENDING);

        if (repairJobs.isEmpty()) {
            return null;
        }
        RepairJob repairJob= repairJobs.get(0);
        repairJob.setTechnician(technicians);
        repairJob.setRepairStatus(RepairStatus.TECHNICIAN_ASSIGNED);
        technicians.setTechnicianStatus(TechnicianStatus.NOT_AVAILABLE);
        technicianRepository.save(technicians);
        repairJobRepository.save(repairJob);
            
        return repairJob;
    }

    public void processJob(RepairJob repairJob, Technicians technician){

        Random random= new Random();
        int time=random.nextInt(5000, 10000);

        repairJob.setRepairStatus(RepairStatus.IN_PROGRESS);

        repairJobRepository.save(repairJob);

        System.out.println(Thread.currentThread().getName()+" --> Technician "+technician.getTechnicianId()+" "+technician.getTechnicianName()+" --> Job "+ repairJob.getJobId()+ " "+repairJob.getJobName());
        try{Thread.sleep(time);}
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        
        //Completion
        repairJob.setRepairStatus(RepairStatus.SUCCESSFULLY_REPAIRED);
        repairJobRepository.save(repairJob);

        technician.setTechnicianStatus(TechnicianStatus.AVAILABLE);
        technicianRepository.save(technician);

        

    }

   
    

}
