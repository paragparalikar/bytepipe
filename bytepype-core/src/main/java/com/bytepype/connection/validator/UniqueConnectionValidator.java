package com.bytepype.connection.validator;

import com.bytepype.connection.Connection;
import com.bytepype.connection.ConnectionRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueConnectionValidator implements ConstraintValidator<UniqueConnection, Connection<?>> {

    private final ConnectionRepository connectionRepository;

    @Override
    public boolean isValid(Connection<?> connection, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        if(connectionRepository.existsByIdNotAndNameIgnoreCase(connection.getId(), connection.getName())){
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    String.format("A different connection with name %s already exists", connection.getName()))
                    .addConstraintViolation();
            valid = false;
        }
        return valid;
    }

}
