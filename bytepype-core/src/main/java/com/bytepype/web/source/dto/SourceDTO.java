package com.bytepype.web.source.dto;

import lombok.Builder;

@Builder
public record SourceDTO(Long id, String name, String description) {

}
