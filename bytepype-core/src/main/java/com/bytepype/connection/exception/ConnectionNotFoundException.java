package com.bytepype.connection.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectionNotFoundException extends RuntimeException {

    private final Long id;

}
