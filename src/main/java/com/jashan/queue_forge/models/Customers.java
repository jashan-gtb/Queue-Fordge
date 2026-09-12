package com.jashan.queue_forge.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customers {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer customer_id;
    private String customer_name;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore // for hard ignoring connected fields
    //@JsonManagedReference // it provides clean json response rather than looping in bi directional mapping
    private List<Devices> devices;

    
}
