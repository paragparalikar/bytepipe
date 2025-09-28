package com.bytepype.dataflow.exception;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DataflowNotFoundException extends RuntimeException {

    Long id;

}
