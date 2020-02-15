package com.fg.infrastructure.model;

import javax.validation.*;
import java.util.Set;

public interface ValidatingModel<T> {

    /**
     * Validates this entity.
     *
     * @throws ConstraintViolationException containing any invalid properties.
     */
    default void validate() {

        // This is a "fancy" way of doing validation. After the entity has been changed in whatever way need be, you
        // then call this method and an exception will be thrown containing anything that's wrong with the entity.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ValidatingModel<T>>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
