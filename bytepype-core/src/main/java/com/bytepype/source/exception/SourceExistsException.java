package com.bytepype.source.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SourceExistsException extends RuntimeException {

    private final String name;

}
