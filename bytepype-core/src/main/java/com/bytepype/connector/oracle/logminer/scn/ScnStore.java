package com.bytepype.connector.oracle.logminer.scn;

import java.math.BigInteger;

public interface ScnStore {

    BigInteger get();

    void put(BigInteger scn);

}
