package com.bytepype.pipeline.web;

import com.bytepype.pipeline.Pipeline;
import com.bytepype.pipeline.PipelineService;
import com.bytepype.pipeline.web.dto.CreatePipelineRequestDTO;
import com.bytepype.pipeline.web.dto.PipelineDTO;
import com.bytepype.pipeline.web.dto.UpdatePipelineRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pipelines")
public class PipelineController {

    private final PipelineMapper pipelineMapper;
    private final PipelineService pipelineService;

    @GetMapping
    public List<PipelineDTO> findAll(){
        return pipelineMapper.toPipelineDTOs(pipelineService.findAll());
    }

    @GetMapping("/{id}")
    public PipelineDTO findById(@PathVariable("id") @NotNull final Long id){
        final Pipeline pipeline = pipelineService.findById(id);
        return pipelineMapper.toPipelineDTO(pipeline);
    }

    @PostMapping
    public PipelineDTO create(@NotNull @Valid @RequestBody final CreatePipelineRequestDTO dto){
        final Pipeline pipeline = pipelineMapper.toPipeline(dto);
        final Pipeline managedPipeline = pipelineService.create(pipeline);
        return pipelineMapper.toPipelineDTO(managedPipeline);
    }

    @PutMapping("/{id}")
    public PipelineDTO update(
            @PathVariable("id") @NotNull final Long id,
            @NotNull @Valid @RequestBody final UpdatePipelineRequestDTO dto){
        final Pipeline pipeline = pipelineMapper.toPipeline(dto);
        pipeline.setId(id);
        final Pipeline managedPipeline = pipelineService.update(pipeline);
        return pipelineMapper.toPipelineDTO(managedPipeline);
    }

    @DeleteMapping("/{id}")
    public PipelineDTO delete(@PathVariable("id") @NotNull final Long id){
        final Pipeline pipeline = pipelineService.delete(id);
        return pipelineMapper.toPipelineDTO(pipeline);
    }

}