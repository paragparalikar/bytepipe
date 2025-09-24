package com.bytepype.oracle.redolog;

import com.bytepype.oracle.scn.ScnRange;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(of = {"rsId", "ssn"})
public class RedoLog implements Comparable<RedoLog>, Serializable {

    private String id;
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
    private Operation operation;
    private ScnRange scnRange;

    public int compareTo(RedoLog other){
        return scn.compareTo(other.scn);
    }

    public String id(){
        return null == id ? id = String.join("-", rsId, String.valueOf(ssn), xid, String.valueOf(scn)) : id;
    }

}
