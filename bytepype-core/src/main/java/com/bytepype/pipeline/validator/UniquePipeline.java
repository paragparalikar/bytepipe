package com.bytepype.pipeline.validator;

import com.bytepype.connection.validator.UniqueConnectionValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = UniquePipelineValidator.class)
public @interface UniquePipeline {
}
