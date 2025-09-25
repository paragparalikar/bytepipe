package com.bytepype.source.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SourceNotFoundException extends RuntimeException {

    private final Long id;

}
