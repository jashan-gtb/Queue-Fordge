package com.jashan.queue_forge.models;

import com.jashan.queue_forge.enums.TechnicianStatus;

import jakarta.persistence.Entity;
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
public class Technicians {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer technicianId;
    private String technicianName;
    private TechnicianStatus technicianStatus;

    @OneToOne 
    @JoinColumn (name = "Repair_job_id")
    private  RepairJob job;

}
