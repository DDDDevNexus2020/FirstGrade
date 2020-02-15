package com.fg.staff.teacher.test;

import com.fg.staff.teacher.api.contract.ChangeAddressCmd;
import com.fg.staff.teacher.model.Address;
import com.fg.staff.teacher.model.Teacher;
import com.fg.test.BaseTest;

import java.util.UUID;

/**
 * Helper methods and properties for Teacher tests.
 */
public abstract class BaseTeacherTest extends BaseTest {

    // ------------------------------------------------ HELPER METHODS -------------------------------------------------

    /**
     * Helper method to create a stub Teacher with a random ID, name, and address.
     */
    protected Teacher createTeacherStub() {
        UUID teacherId = UUID.randomUUID();
        String name = podamFactory.manufacturePojo(String.class);
        Teacher teacher = new Teacher(teacherId, name);
        teacher.changeHomeAddress(this.createAddressStub());
        return teacher;
    }

    /**
     * Helper method to create a stub Address with random values.
     */
    protected Address createAddressStub() {
        return Address.builder() //
                .streetName(podamFactory.manufacturePojo(String.class)) //
                .city(podamFactory.manufacturePojo(String.class)) //
                .state(podamFactory.manufacturePojo(String.class)) //
                .postalCode(podamFactory.manufacturePojo(String.class)) //
                .build();
    }

    /**
     * Helper method to create a randomly populated ChangeAddressCmd.
     */
    protected ChangeAddressCmd createChangeAddressCmd() {
        return ChangeAddressCmd.builder() //
                .streetName(podamFactory.manufacturePojo(String.class)) //
                .city(podamFactory.manufacturePojo(String.class)) //
                .state(podamFactory.manufacturePojo(String.class)) //
                .postalCode(podamFactory.manufacturePojo(String.class)) //
                .build();
    }
}
