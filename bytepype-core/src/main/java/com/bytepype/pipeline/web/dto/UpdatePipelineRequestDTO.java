package com.bytepype.pipeline.web.dto;

public record UpdatePipelineRequestDTO(
        String name,
        boolean enabled,
        String description,
        Long sourceId,
        Long destinationId) {
}
