package com.bytepype.pipeline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Long> {

    boolean existsByIdNotAndNameIgnoreCase(Long id, String name);

    Pipeline findByIdNotAndSourceIdAndDestinationId(Long id, Long sourceId, Long destinationId);

}
