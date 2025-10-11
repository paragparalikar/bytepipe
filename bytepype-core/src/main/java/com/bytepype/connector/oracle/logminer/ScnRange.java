package com.bytepype.connector.oracle.logminer;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

public record ScnRange(@NotNull BigInteger startScn, @NotNull BigInteger endScn) implements Comparable<ScnRange>, Serializable  {
    @Serial private static final long serialVersionUID = 1;

    @Override
    public int compareTo(@NotNull ScnRange other) {
        return startScn.compareTo(other.startScn);
    }
}
