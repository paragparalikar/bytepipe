package com.bytepype.web.source.dto;

import lombok.Builder;

@Builder
public record SourceDetailsDTO(Long id, String name, String description) {

}
