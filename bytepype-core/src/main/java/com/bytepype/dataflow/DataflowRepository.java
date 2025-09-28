package com.bytepype.dataflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataflowRepository extends JpaRepository<Dataflow, Long> {

    boolean existsByIdNotAndNameIgnoreCase(Long id, String name);

    Dataflow findByIdNotAndSourceIdAndDestinationId(Long id, Long sourceId, Long destinationId);

}
