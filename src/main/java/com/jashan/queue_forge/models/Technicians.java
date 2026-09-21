package com.jashan.queue_forge.models;

import com.jashan.queue_forge.enums.TechnicianStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;




@Entity
public class Technicians {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    
    private Integer technicianId;
    private String technicianName;
    @Enumerated (EnumType.STRING)
    private TechnicianStatus technicianStatus;

    @OneToOne
    @MapsId
    private Users user;

    Technicians(Integer technicianId,
                String technicianName,
                TechnicianStatus technicianStatus,
                Users user){
        this.technicianId=technicianId;
        this.technicianName=technicianName;
        this.technicianStatus=technicianStatus;
        this.user=user;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
