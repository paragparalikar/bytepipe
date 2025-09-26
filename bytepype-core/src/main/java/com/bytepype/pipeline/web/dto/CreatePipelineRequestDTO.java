package com.bytepype.pipeline.web.dto;

public record CreatePipelineRequestDTO(
        String name,
        String description,
        Long sourceId,
        Long destinationId) {
}
