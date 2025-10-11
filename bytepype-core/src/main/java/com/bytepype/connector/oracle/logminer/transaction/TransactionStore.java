package com.bytepype.connector.oracle.logminer.transaction;

import com.bytepype.connector.oracle.logminer.scn.ScnRange;

import java.util.Collection;

public interface TransactionStore {

    void delete(ScnRange scnRange);

    void delete(ScnRange scnRange, Transaction transaction);

    boolean exists(ScnRange scnRange);

    Collection<Transaction> getTransactions(ScnRange scnRange);

    void add(ScnRange scnRange, Transaction transaction);

}
