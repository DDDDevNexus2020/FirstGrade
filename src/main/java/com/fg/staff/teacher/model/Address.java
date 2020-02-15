package com.fg.staff.teacher.model;

import lombok.*;

import javax.persistence.Embeddable;

// Domain Model JPA Value Object!
@With // Lombok annotation to create with() factory methods. This is a value object, so should be immutable.
@Getter // Lombok annotation to create public get() methods.
@Builder // Lombok annotation to create builder factory methods.
@Embeddable // JPA annotation indicating this is not an entity, but is "embedded" fields of an entity (a value object)
@EqualsAndHashCode // Lombok annotation to create an all-properties-inclusive equals() and hashCode() methods.
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Required by Spring/JPA. Everyone else must use builder methods.
@AllArgsConstructor(access = AccessLevel.PRIVATE) // Lombok annotation to add an all-args constructor for @Builder.
public class Address {
    private String streetName;
    private String city;
    private String state;
    private String postalCode;
}