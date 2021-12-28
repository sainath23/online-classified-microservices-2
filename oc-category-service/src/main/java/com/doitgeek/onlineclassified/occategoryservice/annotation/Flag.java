package com.doitgeek.onlineclassified.occategoryservice.annotation;

import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.doitgeek.onlineclassified.occategoryservice.constant.ServiceConstant;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FlagValidator.class)
@Documented
public @interface Flag {

    String message() default ErrorMessage.FLAG_MUST_BE_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Flag[] value();
    }
}

class FlagValidator implements ConstraintValidator<Flag, Character> {
    @Override
    public boolean isValid(Character flag, ConstraintValidatorContext context) {
        if (flag == null) return true;
        return ServiceConstant.YES.equals(flag) || ServiceConstant.NO.equals(flag);
    }

    @Override
    public void initialize(Flag constraintAnnotation) {
        // No implementation
    }
}
