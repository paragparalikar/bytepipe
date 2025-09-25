package com.bytepype.web.source.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SourceDTO {

    Long id;
    String name;
    boolean enabled;
    String description;

}
