package com.jashan.queue_forge.models;

import com.jashan.queue_forge.enums.TechnicianStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Technicians {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer technicianId;
    private String technicianName;
    @Enumerated (EnumType.STRING)
    private TechnicianStatus technicianStatus;

    Technicians(Integer technicianId,
                String technicianName,
                TechnicianStatus technicianStatus){
        this.technicianId=technicianId;
        this.technicianName=technicianName;
        this.technicianStatus=technicianStatus;
    }

    public TechnicianStatus getTechnicianStatus() {
        return technicianStatus;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public Integer getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianStatus(TechnicianStatus technicianStatus) {
        this.technicianStatus = technicianStatus;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public void setTechnicianId(Integer technicianId) {
        this.technicianId = technicianId;
    }

    public Technicians() {
    }

    
}
