package com.jashan.queue_forge.models;

import java.util.List;

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
    private List<Devices> devices;

    
}
