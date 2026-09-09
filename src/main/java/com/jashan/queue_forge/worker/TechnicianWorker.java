package com.jashan.queue_forge.worker;

import com.jashan.queue_forge.models.Technicians;
import com.jashan.queue_forge.service.WorkshopService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor 
@NoArgsConstructor 
public class TechnicianWorker implements Runnable{

    Technicians technicians= new Technicians();
    private Integer technicianWorkerId;
    private WorkshopService service;

    @Override 
    public void run(){
        System.out.println("Technician "+technicianWorkerId+ "- "+technicians.getTechnicianName()+" Started working");
    }

}
