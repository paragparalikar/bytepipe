package com.bytepype.dataflow.validator;

import com.bytepype.dataflow.Dataflow;
import com.bytepype.dataflow.DataflowRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueDataflowValidator implements ConstraintValidator<UniqueDataflow, Dataflow> {

    private final DataflowRepository dataflowRepository;

    @Override
    public boolean isValid(Dataflow dataflow, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        if(dataflowRepository.existsByIdNotAndNameIgnoreCase(dataflow.getId(), dataflow.getName())){
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                            String.format("A different dataflow with name %s already exists", dataflow.getName()))
                    .addConstraintViolation();
            valid = false;
        }

        final Dataflow entity = dataflowRepository.findByIdNotAndSourceIdAndDestinationId(dataflow.getId(),
                dataflow.getSource().getId(), dataflow.getDestination().getId());
        if(null != entity){
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                            String.format("A different dataflow with name %s between this source and destination combination already exists",
                                    entity.getName()))
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }

}
