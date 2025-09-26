package com.bytepype.pipeline.validator;

import com.bytepype.pipeline.Pipeline;
import com.bytepype.pipeline.PipelineRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniquePipelineValidator implements ConstraintValidator<UniquePipeline, Pipeline> {

    private final PipelineRepository pipelineRepository;

    @Override
    public boolean isValid(Pipeline pipeline, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        if(pipelineRepository.existsByIdNotAndNameIgnoreCase(pipeline.getId(), pipeline.getName())){
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                            String.format("A different pipeline with name %s already exists", pipeline.getName()))
                    .addConstraintViolation();
            valid = false;
        }

        final Pipeline entity = pipelineRepository.findByIdNotAndSourceIdAndDestinationId(pipeline.getId(),
                pipeline.getSource().getId(), pipeline.getDestination().getId());
        if(null != entity){
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                            String.format("A different pipeline with name %s between this source and destination combination already exists",
                                    entity.getName()))
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }

}
