package com.bytepipe.oracle.scn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.Objects;

@Data
@NoArgsConstructor
@ToString(exclude = {"redoLogCount", "globalLogMinerParallelism"})
@EqualsAndHashCode(exclude = {"redoLogCount", "globalLogMinerParallelism"})
public class ScnRange implements Comparable<ScnRange>, Serializable {

    private BigInteger startScn, endScn;
    private volatile int redoLogCount, globalLogMinerParallelism;

    public ScnRange(BigInteger startScn, BigInteger endScn){
        this.endScn = endScn;
        this.startScn = startScn;
    }

    public boolean contains(ScnRange other){
        return null != other
                && startScn.compareTo(other.startScn) <= 0
                && endScn.compareTo(other.endScn) >= 0;
    }

    @Override
    public int compareTo(ScnRange other){
        return Objects.compare(
                null == startScn ? BigInteger.ZERO : startScn,
                null == other || null == other.startScn ? BigInteger.ZERO : other.startScn,
                Comparator.naturalOrder()
        );
    }

}
