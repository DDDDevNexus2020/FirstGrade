package com.fg.staff.teacher.model;

import lombok.*;

import javax.persistence.Embeddable;

// Domain Model JPA Value Object!
@With // Lombok annotation to create with() factory methods. This is a value object, so should be immutable.
// Lombok annotation to create public get() methods.
// Lombok annotation to create builder factory methods.
@Embeddable // JPA annotation indicating this is not an entity, but is "embedded" fields of an entity (a value object)
// Lombok annotation to create an all-properties-inclusive equals() and hashCode() methods.
// Required by Spring/JPA. Everyone else must use builder methods.
// Lombok annotation to add an all-args constructor for @Builder.
public class Address {
    private String streetName;
    private String city;
    private String state;
    private String postalCode;

    public Address(String streetName, String city, String state, String postalCode) {
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public Address() {
    }

    public static com.fg.staff.teacher.model.Address.AddressBuilder builder() {
        return new AddressBuilder();
    }

    public String getStreetName() {
        return this.streetName;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof com.fg.staff.teacher.model.Address)) {
            return false;
        }
        final com.fg.staff.teacher.model.Address other = (com.fg.staff.teacher.model.Address) o;
        if (!other.canEqual((java.lang.Object) this)) {
            return false;
        }
        final java.lang.Object this$streetName = this.getStreetName();
        final java.lang.Object other$streetName = other.getStreetName();
        if (this$streetName == null ? other$streetName != null : !this$streetName.equals(other$streetName)) {
            return false;
        }
        final java.lang.Object this$city = this.getCity();
        final java.lang.Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) {
            return false;
        }
        final java.lang.Object this$state = this.getState();
        final java.lang.Object other$state = other.getState();
        if (this$state == null ? other$state != null : !this$state.equals(other$state)) {
            return false;
        }
        final java.lang.Object this$postalCode = this.getPostalCode();
        final java.lang.Object other$postalCode = other.getPostalCode();
        if (this$postalCode == null ? other$postalCode != null : !this$postalCode.equals(other$postalCode)) {
            return false;
        }
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof com.fg.staff.teacher.model.Address;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $streetName = this.getStreetName();
        result = result * PRIME + ($streetName == null ? 43 : $streetName.hashCode());
        final java.lang.Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());
        final java.lang.Object $state = this.getState();
        result = result * PRIME + ($state == null ? 43 : $state.hashCode());
        final java.lang.Object $postalCode = this.getPostalCode();
        result = result * PRIME + ($postalCode == null ? 43 : $postalCode.hashCode());
        return result;
    }

    public static class AddressBuilder {
        private String streetName;
        private String city;
        private String state;
        private String postalCode;

        AddressBuilder() {
        }

        public com.fg.staff.teacher.model.Address.AddressBuilder streetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public com.fg.staff.teacher.model.Address.AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public com.fg.staff.teacher.model.Address.AddressBuilder state(String state) {
            this.state = state;
            return this;
        }

        public com.fg.staff.teacher.model.Address.AddressBuilder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public com.fg.staff.teacher.model.Address build() {
            return new Address(streetName, city, state, postalCode);
        }

        public String toString() {
            return "Address.AddressBuilder(streetName=" + this.streetName + ", city=" + this.city + ", state=" +
                   this.state + ", postalCode=" + this.postalCode + ")";
        }
    }
}
