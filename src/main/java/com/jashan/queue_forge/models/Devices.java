package com.jashan.queue_forge.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Devices {
    
    @Id
    private Integer deviceId;
    private String deviceName;
    
    @ManyToOne 
    @JoinTable (
        name = "customer_device",
        joinColumns ={
            @JoinColumn(name = "Customer_id")
        },
        inverseJoinColumns = {
            @JoinColumn (name="Device_id")
        }
        )
   
    private Customers customer;

    @OneToOne 
    @JoinColumn (name ="Assigned_Technician_id")
    private Technicians technician;

    @OneToOne 
    @JoinColumn (name = "Repair_job_id")
    private RepairJob repairJob;

}
