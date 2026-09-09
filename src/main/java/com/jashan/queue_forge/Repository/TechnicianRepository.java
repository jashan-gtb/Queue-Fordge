package com.jashan.queue_forge.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jashan.queue_forge.models.Technicians;
import com.jashan.queue_forge.enums.TechnicianStatus;


public interface TechnicianRepository extends JpaRepository<Technicians,Integer>{

    List<Technicians> findByTechnicianStatus(TechnicianStatus technicianStatus);

}
