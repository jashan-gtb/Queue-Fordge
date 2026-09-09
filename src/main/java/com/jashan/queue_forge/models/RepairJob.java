package com.jashan.queue_forge.models;
import com.jashan.queue_forge.enums.Priority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Entity 
public class RepairJob {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer jobId;
    private String jobName;
    @Enumerated (EnumType.STRING)
    private Priority priority;
    
    @OneToOne 
    @JoinColumn (name = "Device_id")
    private Devices device;

    @OneToOne 
    @JoinColumn (name="Assigned_Technician_id")
    private Technicians technician;


}
