package com.fg.staff.teacher.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

// Domain Model JPA Entity!
@Entity // JPA annotation marking this class as a JPA entity. Also convenient because this class is a DDD-style entity!
@Getter // Lombok annotation to make public get() methods.
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Required by Spring/JPA. Everyone else must use builder methods.
public class Teacher {

    // --------------------------------------------------- PROPERTIES --------------------------------------------------

    @Id // JPA annotation designating this property as the unique ID of this entity...
    @GeneratedValue(generator = "UUID") // ... and that it should use an ID generator called "UUID"
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator") // ... which is defined right here
    private UUID id; // There's no way for application code to set this. JPA is the only one that can.

    @NotBlank(message = "The teacher name is required.") // Spring Validation annotation. See "validate()".
    private String name;

    private Address homeAddress = Address.builder().build();

    // ------------------------------------------------- CONSTRUCTOR ---------------------------------------------------

    /**
     * Create a new Teacher with the given name.
     */
    public Teacher(String name) {
        this.name = name;
    }

    /**
     * Create a new Teacher with the given ID and name.
     */
    public Teacher(UUID id, String name) {
        this(name);
        this.id = id;
    }

    // ------------------------------------------------ PUBLIC METHODS -------------------------------------------------

    /**
     * Change the name of this Teacher to the given name.
     *
     * @param name the new name of the Teacher.
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * Change the home address of this Teacher to the given address.
     *
     * @param homeAddress the new home address of the Teacher.
     */
    public void changeHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * Validates this entity.
     *
     * @throws ConstraintViolationException containing any invalid properties.
     */
    public void validate() {

        // This is a "fancy" way of doing validation. After the entity has been changed in whatever way need be, you
        // then call this method and an exception will be thrown containing anything that's wrong with the entity.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Teacher>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
