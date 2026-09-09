package com.jashan.queue_forge.models;

import com.jashan.queue_forge.enums.TechnicianStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Enumerated (EnumType.STRING)
    private TechnicianStatus technicianStatus;



}
