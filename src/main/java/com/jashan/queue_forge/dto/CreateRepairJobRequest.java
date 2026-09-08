package com.jashan.queue_forge.dto;

import com.jashan.queue_forge.enums.Priority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class CreateRepairJobRequest {

   
    private Integer deviceId;
    private String problem;
    private Priority priority;

}
