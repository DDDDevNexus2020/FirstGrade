package com.fg.staff.teacher.api;

import com.fg.staff.teacher.api.contract.TeacherData;
import com.fg.staff.teacher.model.Teacher;
import com.fg.staff.teacher.test.BaseTeacherTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TeacherTranslatorTest extends BaseTeacherTest {

    // ----------------------------------------------- MEMBER VARIABLES ------------------------------------------------

    // Class under test
    @Spy
    private TeacherTranslator teacherTranslator;


    // ------------------------------------------------- TEST METHODS --------------------------------------------------

    /**
     * GIVEN a fully populated Teacher entity, WHEN it is translated from an entity into a Teacher contract, THEN all
     * properties should be correctly mapped.
     */
    @Test
    void translate() {

        // GIVEN a fully populated Teacher entity
        Teacher teacherStub = this.createTeacherStub();

        // WHEN it is translated from an entity into a Teacher contract
        TeacherData teacherData = teacherTranslator.translate(teacherStub);

        // THEN all properties should be correctly mapped.
        assertEquals(teacherStub.getId().toString(), teacherData.getId());
        assertEquals(teacherStub.getName(), teacherData.getName());
        assertEquals(teacherStub.getHomeAddress().getStreetName(), teacherData.getHomeStreetName());
        assertEquals(teacherStub.getHomeAddress().getCity(), teacherData.getHomeCity());
        assertEquals(teacherStub.getHomeAddress().getState(), teacherData.getHomeState());
        assertEquals(teacherStub.getHomeAddress().getPostalCode(), teacherData.getHomePostalCode());

    }
}