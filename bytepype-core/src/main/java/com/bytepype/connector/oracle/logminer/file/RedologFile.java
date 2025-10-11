package com.bytepype.connector.oracle.logminer.file;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigInteger;

@Builder
public record RedologFile(
        @NotNull String name,
        @NotNull String status,
        @NotNull Boolean archived,
        @NotNull BigInteger thread,
        @NotNull BigInteger sequence,
        @NotNull BigInteger firstChange,
        @NotNull BigInteger nextChange) implements Comparable<RedologFile>, Serializable {

    @Override
    public int compareTo(RedologFile other) {
        return this.firstChange.compareTo(other.firstChange);
    }
}
