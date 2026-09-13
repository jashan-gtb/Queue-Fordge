package com.jashan.queue_forge.dto;

public record LoginResponseDto(
    String jwt,
    Integer userId
) {

}
