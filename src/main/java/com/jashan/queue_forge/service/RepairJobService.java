package com.jashan.queue_forge.service;

import org.springframework.stereotype.Service;

import com.jashan.queue_forge.Repository.DeviceRepository;
import com.jashan.queue_forge.Repository.RepairJobRepository;
import com.jashan.queue_forge.dto.CreateRepairJobRequest;
import com.jashan.queue_forge.enums.Priority;
import com.jashan.queue_forge.models.Devices;
import com.jashan.queue_forge.models.RepairJob;

@Service 
public class RepairJobService {

    private final RepairJobRepository repairJobRepository;

    private final DeviceRepository deviceRepository;

    RepairJobService(RepairJobRepository repairJobRepository,
                    DeviceRepository deviceRepository){

        this.deviceRepository=deviceRepository;
        this.repairJobRepository=repairJobRepository;
       }

    public RepairJob creatRepairJob(CreateRepairJobRequest request){

        Devices devices= deviceRepository.findById(request.getDeviceId()).orElseThrow(() -> new RuntimeException("Device not found"));
        RepairJob job=new RepairJob();

        job.setDevice(devices);
        job.setJobName(request.getProblem());
        //Priority priority = request.getPriority();

        return repairJobRepository.save(job);
    }
    

}
