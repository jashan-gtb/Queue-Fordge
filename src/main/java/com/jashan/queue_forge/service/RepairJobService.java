package com.jashan.queue_forge.service;

import org.springframework.stereotype.Service;

import com.jashan.queue_forge.Repository.CustomerRepository;
import com.jashan.queue_forge.Repository.DeviceRepository;
import com.jashan.queue_forge.Repository.RepairJobRepository;
import com.jashan.queue_forge.dto.RepairJobDto;
import com.jashan.queue_forge.dto.RepairJobResponseDto;
import com.jashan.queue_forge.enums.RepairStatus;
import com.jashan.queue_forge.models.Customers;
import com.jashan.queue_forge.models.Devices;
import com.jashan.queue_forge.models.RepairJob;

import jakarta.transaction.Transactional;

@Service 
public class RepairJobService {

    private final CustomerRepository customerRepository;
    private  final DeviceRepository deviceRepository;
    private  final RepairJobRepository repairJobRepository;

    RepairJobService(CustomerRepository customerRepository,
                    DeviceRepository deviceRepository,
                    RepairJobRepository repairJobRepository
    ){
        this.customerRepository=customerRepository;
        this.repairJobRepository=repairJobRepository;
        this.deviceRepository=deviceRepository;
    }

    @Transactional 
    public RepairJobResponseDto creatRepairJob(RepairJobDto dto){

        //Map and save customer
        Customers customers = new Customers();
        customers.setCustomer_name(dto.customer());
        customers=customerRepository.save(customers);

        //Map and save device
        Devices devices=new Devices();
        devices.setDeviceName(dto.deviceCompany());
        devices.setDeviceType(dto.device());
        devices.setCustomer(customers);
    

        // Map and save job

        RepairJob repairJob=new RepairJob();
        repairJob.setJobName(dto.problem());
        repairJob.setPriority(dto.priority());
        repairJob.setDevice(devices);
        repairJob.setRepairStatus(RepairStatus.PENDING);
        
        devices.setRepairJob(repairJob);
        devices=deviceRepository.save(devices);

        RepairJob savedJob= repairJobRepository.save(repairJob);

        return new RepairJobResponseDto(
            savedJob.getJobId(),
            savedJob.getJobName(),
            customers.getCustomer_name(),
            devices.getDeviceName(),
            savedJob.getPriority().name(),
            RepairStatus.PENDING);

       
    }

     
}
