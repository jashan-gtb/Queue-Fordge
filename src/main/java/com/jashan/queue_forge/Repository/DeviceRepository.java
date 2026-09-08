package com.jashan.queue_forge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jashan.queue_forge.models.Devices;

public interface DeviceRepository extends JpaRepository<Devices,Integer>{

}
