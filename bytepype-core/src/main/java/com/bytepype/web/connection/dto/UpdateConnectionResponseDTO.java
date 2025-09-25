package com.bytepype.web.connection.dto;

import com.bytepype.connection.ConnectionType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateConnectionResponseDTO {

    Long id;
    String name;
    String description;
    ConnectionType type;

}
