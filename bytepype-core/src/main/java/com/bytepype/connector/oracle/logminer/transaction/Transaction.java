package com.bytepype.connector.oracle.logminer.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigInteger;

@Builder
public record Transaction(
        @NotNull String xid,
        @NotNull BigInteger scn,
        @NotNull Long lastTimestamp,
        @NotNull TransactionStatus status) {
}
