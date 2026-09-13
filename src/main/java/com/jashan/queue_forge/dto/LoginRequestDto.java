package com.jashan.queue_forge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequestDto(
    String userName,
    @JsonProperty("password")String userPass
) {

}
