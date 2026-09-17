package com.jashan.queue_forge.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Devices {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer deviceId;
    private String deviceName;
    private String deviceType;
    
    @ManyToOne 
   /* @JoinTable (
        name = "customer_device",
        joinColumns ={
            @JoinColumn(name = "Customer_id")
        },
        inverseJoinColumns = {
            @JoinColumn (name="Device_id")
        }
        )*/

    @JoinColumn (name = "Customer_id")
    @JsonIgnoreProperties ("devices")
    private Customers customer;


    @OneToOne 
    @JoinColumn (name = "Repair_job_id")
    private RepairJob repairJob;


    public Integer getDeviceId() {
        return deviceId;
    }


    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }


    public String getDeviceName() {
        return deviceName;
    }


    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    public String getDeviceType() {
        return deviceType;
    }


    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }


    public Customers getCustomer() {
        return customer;
    }


    public void setCustomer(Customers customer) {
        this.customer = customer;
    }


    public RepairJob getRepairJob() {
        return repairJob;
    }


    public void setRepairJob(RepairJob repairJob) {
        this.repairJob = repairJob;
    }


    public Devices(Integer deviceId, String deviceName, String deviceType, Customers customer, RepairJob repairJob) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.customer = customer;
        this.repairJob = repairJob;
    }


    public Devices() {
    }

    

}
