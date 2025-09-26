package com.bytepype.connection.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class OracleConnectionDTO extends ConnectionDTO {

    private String url;
    private String username;

}
