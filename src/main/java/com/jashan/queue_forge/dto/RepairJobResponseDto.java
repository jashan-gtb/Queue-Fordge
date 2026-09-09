package com.jashan.queue_forge.dto;

import com.jashan.queue_forge.enums.RepairStatus;

public record RepairJobResponseDto(
    Integer jobId,
    String jobName,
    String customer,
    String device,
    String priority,
    RepairStatus repairStatus

) {
}
