package com.bytepype.connector.oracle.logminer.redolog;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;

public class InMemoryRedologStore implements RedologStore {

    private final MultiValueMap<String, Redolog> cache = new LinkedMultiValueMap<>();

    @Override
    public void put(Redolog redolog) {
        cache.add(redolog.getXid(), redolog);
    }

    @Override
    public List<Redolog> findByXid(String xid) {
        return cache.getOrDefault(xid, Collections.emptyList());
    }

    @Override
    public void deleteByXid(String xid) {
        cache.remove(xid);
    }
}
