package com.bytepype.web.connection.dto;

import com.bytepype.connection.ConnectionType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OracleConnectionDetailsDTO.class, name = "ORACLE")
})
public class ConnectionDetailsDTO {

    private Long id;
    private String name;
    private String description;
    private ConnectionType type;

}
