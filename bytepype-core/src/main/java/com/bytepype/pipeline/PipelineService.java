package com.bytepype.pipeline;

import com.bytepype.pipeline.exception.PipelineNotFoundException;
import com.bytepype.pipeline.validator.UniquePipeline;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PipelineService {

    private final PipelineRepository pipelineRepository;

    public List<Pipeline> findAll(){
        return pipelineRepository.findAll();
    }

    public Pipeline findById(@NotNull final Long id){
        return pipelineRepository.findById(id)
                .orElseThrow(() -> new PipelineNotFoundException(id));
    }

    public Pipeline create(@NotNull @Valid @UniquePipeline final Pipeline pipeline){
        pipeline.setId(null);
        return pipelineRepository.save(pipeline);
    }

    public Pipeline update(@NotNull @Valid @UniquePipeline final Pipeline pipeline){
        if(!pipelineRepository.existsById(pipeline.getId())){
            throw new PipelineNotFoundException(pipeline.getId());
        }
        return pipelineRepository.save(pipeline);
    }

    public Pipeline delete(@NotNull final Long id){
        final Pipeline pipeline = pipelineRepository.findById(id)
                .orElseThrow(() -> new PipelineNotFoundException(id));
        pipelineRepository.delete(pipeline);
        return pipeline;
    }

}
