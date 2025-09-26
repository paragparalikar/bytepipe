package com.bytepype.pipeline.web;

import com.bytepype.pipeline.Pipeline;
import com.bytepype.pipeline.web.dto.CreatePipelineRequestDTO;
import com.bytepype.pipeline.web.dto.PipelineDTO;
import com.bytepype.pipeline.web.dto.UpdatePipelineRequestDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PipelineMapper {

    Pipeline toPipeline(CreatePipelineRequestDTO dto);

    Pipeline toPipeline(UpdatePipelineRequestDTO dto);

    PipelineDTO toPipelineDTO(Pipeline pipeline);

    List<PipelineDTO> toPipelineDTOs(List<Pipeline> pipelines);

}
