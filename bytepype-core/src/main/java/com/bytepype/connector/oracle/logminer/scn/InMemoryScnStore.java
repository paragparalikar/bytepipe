package com.bytepype.connector.oracle.logminer.scn;

import java.math.BigInteger;

public class InMemoryScnStore implements ScnStore {

    private volatile BigInteger scn;

    @Override
    public BigInteger get() {
        return scn;
    }

    @Override
    public void put(BigInteger scn) {
        this.scn = scn;
    }
}
