package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class EmployeeDeleteDto {

    @Schema(description = "The deletion of some user by id", example = "1", required = true)
    public Integer id;
}
