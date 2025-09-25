package com.bytepype.web.source.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class SourceDetailsDTO {

    Long id;
    String name;
    boolean enabled;
    String description;



}
