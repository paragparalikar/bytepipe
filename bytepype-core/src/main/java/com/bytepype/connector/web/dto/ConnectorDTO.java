package com.bytepype.connector.web.dto;

import com.bytepype.connector.ConnectorType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.Instant;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OracleConnectorDTO.class, name = "ORACLE")
})
public sealed class ConnectorDTO permits OracleConnectorDTO {
    private Long id;
    private String name;
    private String description;
    private ConnectorType type;
    private String createdBy;
    private Instant createdDate;
    private String lastModifiedBy;
    private Instant lastModifiedDate;
}
