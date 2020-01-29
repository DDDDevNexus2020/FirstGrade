package com.fg.staff.teacher.api.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

// API Value Object!
@With // Lombok annotation to create with() factory methods. This is a value object, so should be immutable.
@Builder // Lombok annotation to create builder factory methods.
@Getter // Lombok annotation to create public get() methods.
@EqualsAndHashCode // Lombok annotation to create an all-properties-inclusive equals() and hashCode() methods.
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Required by Spring/JPA. Everyone else must use builder methods.
@AllArgsConstructor(access = AccessLevel.PRIVATE) // Lombok annotation to add an all-args constructor for @Builder.
public class ChangeAddressCmd {

    @JsonProperty("streetName") // Jackson annotation to specify the property name in the JSON body.
    @NotBlank(message = "The street name is required.") // Spring annotation used by @Valid in the REST Controller.
    private String streetName;

    @JsonProperty("city")
    @NotBlank(message = "The city is required.")
    private String city;

    @JsonProperty("state")
    @NotBlank(message = "The state is required.")
    private String state;

    @JsonProperty("postalCode")
    @NotBlank(message = "The postal code is required.")
    private String postalCode;
}
