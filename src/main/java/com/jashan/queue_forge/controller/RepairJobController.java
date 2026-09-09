package com.jashan.queue_forge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jashan.queue_forge.dto.RepairJobDto;
import com.jashan.queue_forge.dto.RepairJobResponseDto;
import com.jashan.queue_forge.service.RepairJobService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping ("/repair-jobs")
public class RepairJobController {

   private final RepairJobService repairJobService;

   RepairJobController(RepairJobService repairJobService){
    this.repairJobService=repairJobService;
   }

   @PostMapping("")
   public RepairJobResponseDto postJob(@RequestBody RepairJobDto dto) {
       
       return repairJobService.creatRepairJob(dto);
   }
   
    
    


}




    

    
    
    

