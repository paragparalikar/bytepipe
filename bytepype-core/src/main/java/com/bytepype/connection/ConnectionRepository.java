package com.bytepype.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection<?>, Long>, QueryByExampleExecutor<Connection<?>> {

    boolean existsByIdNotAndNameIgnoreCase(Long id, String name);

}
