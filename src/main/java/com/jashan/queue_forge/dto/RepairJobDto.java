package com.jashan.queue_forge.dto;

import com.jashan.queue_forge.enums.Priority;

public record RepairJobDto(
   String customer,
   String device,
   String deviceCompany,
   String problem,
   Priority priority

) {

}
