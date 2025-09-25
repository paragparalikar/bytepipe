package com.bytepype.web.connection.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OracleConnectionDetailsDTO extends ConnectionDetailsDTO{

    private String url;
    private String username;

}
