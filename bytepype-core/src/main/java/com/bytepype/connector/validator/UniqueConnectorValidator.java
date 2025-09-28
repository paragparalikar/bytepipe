package com.bytepype.connector.validator;

import com.bytepype.connector.Connector;
import com.bytepype.connector.ConnectorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueConnectorValidator implements ConstraintValidator<UniqueConnector, Connector<?>> {

    private final ConnectorRepository connectorRepository;

    @Override
    public boolean isValid(Connector<?> connector, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        if(connectorRepository.existsByIdNotAndNameIgnoreCase(connector.getId(), connector.getName())){
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    String.format("A different connector with name %s already exists", connector.getName()))
                    .addConstraintViolation();
            valid = false;
        }
        return valid;
    }

}
