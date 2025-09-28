package com.bytepype.connector.exception;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConnectorNotFoundException extends RuntimeException {

    Long id;

}
