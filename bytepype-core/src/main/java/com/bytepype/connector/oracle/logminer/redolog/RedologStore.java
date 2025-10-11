package com.bytepype.connector.oracle.logminer.redolog;

import java.util.List;

public interface RedologStore {

    void put(Redolog redolog);

    List<Redolog> findByXid(String xid);

    void deleteByXid(String xid);

}
