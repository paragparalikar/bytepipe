package com.bytepype.dataflow.web.dto;

public record CreateDataflowRequestDTO(
        String name,
        String description,
        Long sourceId,
        Long destinationId) {
}
