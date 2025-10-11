package com.bytepype.connector.oracle.logminer.transaction;

import com.bytepype.connector.oracle.logminer.scn.ScnRange;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.Collections;

public class InMemoryTransactionStore implements TransactionStore {

    private final MultiValueMap<ScnRange, Transaction> cache = new LinkedMultiValueMap<>();

    @Override
    public void delete(ScnRange scnRange) {
        cache.remove(scnRange);
    }

    @Override
    public void delete(ScnRange scnRange, Transaction transaction) {
        cache.getOrDefault(scnRange, Collections.emptyList()).remove(transaction);
    }

    @Override
    public boolean exists(ScnRange scnRange) {
        return cache.containsKey(scnRange);
    }

    @Override
    public Collection<Transaction> getTransactions(ScnRange scnRange) {
        return cache.get(scnRange);
    }

    @Override
    public void add(ScnRange scnRange, Transaction transaction) {
        cache.add(scnRange, transaction);
    }
}
