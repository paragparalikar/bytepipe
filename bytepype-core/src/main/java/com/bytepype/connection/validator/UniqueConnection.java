package com.bytepype.connection.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = UniqueConnectionValidator.class)
public @interface UniqueConnection {
}
