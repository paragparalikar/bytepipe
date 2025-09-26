package com.bytepype.pipeline.web;

import com.bytepype.connection.web.ConnectionMapper;
import com.bytepype.pipeline.Pipeline;
import com.bytepype.pipeline.web.dto.CreatePipelineRequestDTO;
import com.bytepype.pipeline.web.dto.PipelineDTO;
import com.bytepype.pipeline.web.dto.UpdatePipelineRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ConnectionMapper.class)
public interface PipelineMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "source", source = "sourceId")
    @Mapping(target = "destination", source = "destinationId")
    Pipeline toPipeline(CreatePipelineRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "source", source = "sourceId")
    @Mapping(target = "destination", source = "destinationId")
    Pipeline toPipeline(UpdatePipelineRequestDTO dto);

    @Mapping(target = "sourceId", source = "source.id")
    @Mapping(target = "destinationId", source = "destination.id")
    PipelineDTO toPipelineDTO(Pipeline pipeline);

    List<PipelineDTO> toPipelineDTOs(List<Pipeline> pipelines);

}
