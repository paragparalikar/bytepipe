package com.bytepype.dataflow.web.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record DataflowDTO(
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
