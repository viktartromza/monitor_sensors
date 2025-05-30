package by.agsr.monitorsensors.util.validation.constraint;

import by.agsr.monitorsensors.model.dto.RangeDto;
import by.agsr.monitorsensors.util.validation.annotation.ValidRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class RangeConstraintValidator implements ConstraintValidator<ValidRange, RangeDto> {

    @Override
    public void initialize(ValidRange annotation) {
        ConstraintValidator.super.initialize(annotation);
    }

    @Override
    public boolean isValid(RangeDto rqDto, ConstraintValidatorContext constraintValidatorContext) {
        var from = rqDto.getFrom();
        var to = rqDto.getTo();
        if(Objects.nonNull(from)&& Objects.nonNull(to)){
            return from<to;
        }
        return true;
    }
}