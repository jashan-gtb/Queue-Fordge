package com.jashan.queue_forge.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.OneToMany;



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

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    public void setDevices(List<Devices> devices) {
        this.devices = devices;
    }

    public Customers(Integer customer_id, String customer_name, List<Devices> devices) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.devices = devices;
    }

    public Customers() {
    }

    
}
