package com.bytepype.dataflow.web.dto;

public record UpdateDataflowRequestDTO(
        String name,
        boolean enabled,
        String description,
        Long sourceId,
        Long destinationId) {
}
