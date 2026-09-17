package com.jashan.queue_forge.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jashan.queue_forge.enums.Priority;
import com.jashan.queue_forge.enums.RepairStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity 
public class RepairJob {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer jobId;
    private String jobName;
    @Enumerated (EnumType.STRING)
    private Priority priority;
    @Enumerated (EnumType.STRING)
    private RepairStatus repairStatus;
    
    @OneToOne 
    @JoinColumn (name = "Device_id")
    @JsonIgnoreProperties("repairJob") 
    private Devices device;

    @ManyToOne
    @JoinColumn (name="Assigned_Technician_id")
    private Technicians technician;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public RepairStatus getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(RepairStatus repairStatus) {
        this.repairStatus = repairStatus;
    }

    public Devices getDevice() {
        return device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }

    public Technicians getTechnician() {
        return technician;
    }

    public void setTechnician(Technicians technician) {
        this.technician = technician;
    }

    public RepairJob() {
    }

    public RepairJob(Integer jobId, String jobName, Priority priority, RepairStatus repairStatus, Devices device,
            Technicians technician) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.priority = priority;
        this.repairStatus = repairStatus;
        this.device = device;
        this.technician = technician;
    }

    
}
