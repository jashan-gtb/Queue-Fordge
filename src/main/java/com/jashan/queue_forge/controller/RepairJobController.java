package com.jashan.queue_forge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jashan.queue_forge.dto.CreateRepairJobRequest;
import com.jashan.queue_forge.models.RepairJob;
import com.jashan.queue_forge.service.RepairJobService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/repair-jobs")
public class RepairJobController {

    private RepairJobService repairJobService;

    @PostMapping("")
    private RepairJob createJob(@RequestBody CreateRepairJobRequest request){

        return repairJobService.creatRepairJob(request);
    }


    

    
    
    
}
