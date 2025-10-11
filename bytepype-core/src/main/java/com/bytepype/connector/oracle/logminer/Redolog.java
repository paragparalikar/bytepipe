package com.bytepype.connector.oracle.logminer;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Data
public class Redolog implements Comparable<Redolog>, Serializable {
    @Serial private static final long serialVersionUID = 1;

    private String rsId;
    private String xid;
    private String sqlRedo;
    private String username;
    private String clientId;
    private String tableName;
    private String schemaName;

    private BigInteger ssn;
    private BigInteger scn;
    private BigInteger csf;
    private BigInteger rowNum;

    private Long timestamp;
    private ScnRange scnRange;
    private Operation operation;


    @Override
    public int compareTo(Redolog other) {
        return this.scn.compareTo(other.scn);
    }
}
