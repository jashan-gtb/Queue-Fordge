package com.jashan.queue_forge.controller;

import org.apache.tomcat.util.http.parser.Priority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jashan.queue_forge.Repository.RepairJobRepository;
import com.jashan.queue_forge.dto.RepairJobDto;
import com.jashan.queue_forge.models.Customers;
import com.jashan.queue_forge.models.Devices;
import com.jashan.queue_forge.models.RepairJob;

import lombok.var;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping ("/repair-jobs")
public class RepairJobController {

    private RepairJobRepository repairJobRepository;

    RepairJobController(RepairJobRepository repairJobRepository){
        this.repairJobRepository=repairJobRepository;
    }

    @PostMapping("")
    public RepairJob postJob(@RequestBody RepairJob dto) {
   
        
        return repairJobRepository.save(dto);}
    
    
    private RepairJob toJob(RepairJobDto dto){
        var job= new RepairJob();
        job.setJobName(dto.problem());

        var device = new Devices();
        device.setDeviceName(dto.device());
        device.setDeviceType(dto.deviceCompany());

        var customer = new Customers();
        customer.setCustomer_name(dto.customer());

        return repairJobRepository.save(toJob(dto));

        
    }



}




    

    
    
    

