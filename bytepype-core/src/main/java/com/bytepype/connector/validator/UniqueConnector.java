package com.bytepype.connector.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = UniqueConnectorValidator.class)
public @interface UniqueConnector {
}
