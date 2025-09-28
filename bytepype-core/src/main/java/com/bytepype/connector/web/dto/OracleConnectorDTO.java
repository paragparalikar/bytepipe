package com.bytepype.connector.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class OracleConnectorDTO extends ConnectorDTO {

    private String url;
    private String username;

}
