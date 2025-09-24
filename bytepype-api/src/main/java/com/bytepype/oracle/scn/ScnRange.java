package com.bytepype.oracle.scn;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.Objects;


public record ScnRange(BigInteger startScn, BigInteger endScn) implements Comparable<ScnRange>, Serializable {

    public boolean contains(ScnRange other) {
        return null != other
                && startScn.compareTo(other.startScn) <= 0
                && endScn.compareTo(other.endScn) >= 0;
    }

    @Override
    public int compareTo(ScnRange other) {
        return Objects.compare(
                null == startScn ? BigInteger.ZERO : startScn,
                null == other || null == other.startScn ? BigInteger.ZERO : other.startScn,
                Comparator.naturalOrder()
        );
    }

}
