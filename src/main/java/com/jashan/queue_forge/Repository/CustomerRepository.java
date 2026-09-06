package com.jashan.queue_forge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jashan.queue_forge.models.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer>{

}
