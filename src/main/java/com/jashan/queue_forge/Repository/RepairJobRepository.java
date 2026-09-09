package com.jashan.queue_forge.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jashan.queue_forge.models.RepairJob;
import com.jashan.queue_forge.enums.Priority;
import com.jashan.queue_forge.enums.RepairStatus;


public interface RepairJobRepository extends JpaRepository<RepairJob, Integer>{

    List<RepairJob> findByPriority(Priority priority);
    List<RepairJob> findByRepairStatusOrderByPriorityDesc(RepairStatus status);

}
