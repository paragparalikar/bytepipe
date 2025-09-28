package com.bytepype.dataflow.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = UniqueDataflowValidator.class)
public @interface UniqueDataflow {
}
