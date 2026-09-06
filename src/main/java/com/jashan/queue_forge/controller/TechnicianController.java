package com.jashan.queue_forge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jashan.queue_forge.Repository.TechnicianRepository;
import com.jashan.queue_forge.models.Technicians;


@RestController 
@RequestMapping ("/technician")
public class TechnicianController {
    private final TechnicianRepository technicianRepository;

    public TechnicianController(TechnicianRepository technicianRepository){
        this.technicianRepository=technicianRepository;
    }

    @GetMapping("")
    public List<Technicians> findAll(){

        return technicianRepository.findAll();
    }

    @GetMapping("/{id}")
    public Technicians findTechById(@PathVariable Integer id) {
        return technicianRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    
    }
    
    @PostMapping("")
    public void  postTech(@RequestBody Technicians technician) {
        technicianRepository.save(technician);
    }

    @PutMapping("/{id}")
    public void updateTech(@PathVariable Integer id, @RequestBody Technicians technician) {

        if (!technicianRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            
        }
        technician.setTechnicianId(id);
        technicianRepository.save(technician);
    }

    @DeleteMapping("/{id}")
    public void deleteTechById(@PathVariable Integer id){
        technicianRepository.deleteById(id);
    }

}
