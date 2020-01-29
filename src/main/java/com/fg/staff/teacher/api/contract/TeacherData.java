package com.fg.staff.teacher.api.contract;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

// API Value Object!
@With // Lombok annotation to create with() factory methods. This is a value object, so should be immutable.
@Builder // Lombok annotation to create builder factory methods.
@Getter // Lombok annotation to create public get() methods.
@EqualsAndHashCode(callSuper = true) // Lombok annotation needed to include RepresentationModel equality, too.
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Required by Spring/JPA. Everyone else must use builder methods.
@AllArgsConstructor(access = AccessLevel.PRIVATE) // Lombok annotation to add an all-args constructor for @Builder.
public class TeacherData extends RepresentationModel<TeacherData> {
    private String id;
    private String name;
    private String homeStreetName;
    private String homeCity;
    private String homeState;
    private String homePostalCode;
}

