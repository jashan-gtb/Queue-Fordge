package com.jashan.queue_forge.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer deviceId;
    private String deviceName;
    private String deviceType;
    
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
    @JoinColumn (name = "Repair_job_id")
    private RepairJob repairJob;


}
