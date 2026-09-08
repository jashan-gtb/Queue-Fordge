package com.jashan.queue_forge.models;
import jakarta.persistence.Entity;
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
    private Integer jobId;
    private String jobName;
    
    @OneToOne 
    @JoinColumn (name = "Device_id")
    private Devices device;

    @OneToOne 
    @JoinColumn (name="Assigned_Technician_id")
    private Technicians technician;


}
