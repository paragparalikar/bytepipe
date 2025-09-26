package com.bytepype.connection.web.dto;

import com.bytepype.connection.ConnectionType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.Instant;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OracleConnectionDTO.class, name = "ORACLE")
})
public sealed class ConnectionDTO permits OracleConnectionDTO {
    private Long id;
    private String name;
    private String description;
    private ConnectionType type;
    private String createdBy;
    private Instant createdDate;
    private String lastModifiedBy;
    private Instant lastModifiedDate;
}
