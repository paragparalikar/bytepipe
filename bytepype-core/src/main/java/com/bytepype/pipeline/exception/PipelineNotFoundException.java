package com.bytepype.pipeline.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PipelineNotFoundException extends RuntimeException {

    private final Long id;

}
