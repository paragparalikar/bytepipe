package com.bytepype.connector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectorRepository extends JpaRepository<Connector<?>, Long> {

    boolean existsByIdNotAndNameIgnoreCase(Long id, String name);

}
