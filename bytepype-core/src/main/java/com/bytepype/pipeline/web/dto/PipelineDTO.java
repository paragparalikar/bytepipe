package com.bytepype.pipeline.web.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record PipelineDTO(
        Long id,
        String name,
        boolean enabled,
        String description,
        Long sourceId,
        Long destinationId,
        String createdBy,
        Instant createdDate,
        String lastModifiedBy,
        Instant lastModifiedDate) {
}
