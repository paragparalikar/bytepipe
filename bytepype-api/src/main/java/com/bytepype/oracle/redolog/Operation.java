package com.bytepype.oracle.redolog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operation {

    INSERT(true), DIRECT_INSERT(true), UPDATE(true), DELETE(true),
    DDL(false), START(false), COMMIT(false), ROLLBACK(false), SELECT_FOR_UPDATE(false),
    LOB_WRITE(false), LOB_TRIM(false), LOB_ERASE(false), SEL_LOB_LOCATOR(false),
    MISSING_SCN(false), INTERNAL(false), UNSUPPORTED(false);

    private final boolean dml;

}
