package com.fg.staff.teacher.infrastructure;

import com.fg.staff.teacher.model.Teacher;
import com.fg.staff.teacher.test.BaseTeacherTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Note that this isn't testing JPA, it is testing that the JPA annotations we've placed on our domain model classes are
 * functioning. This is using H2 in a unit testing capacity, and therefore isn't an integration test (which would access
 * an external database directly).
 */
@DataJpaTest // SpringBoot annotation to initialize JPA context for unit testing.
class TeacherRepositoryTest extends BaseTeacherTest {

    // ----------------------------------------------- MEMBER VARIABLES ------------------------------------------------

    // Class under test
    @Autowired
    private TeacherRepository teacherRepository;


    // ------------------------------------------------- TEST METHODS --------------------------------------------------

    /**
     * GIVEN a fully populated Teacher entity, WHEN the Teacher is persisted, THEN the Teacher should be readable from
     * the database.
     */
    @Test
    void verifySave() {

        // GIVEN a fully populated Teacher entity,
        String name = podamFactory.manufacturePojo(String.class);
        Teacher expectedTeacher = new Teacher(name);
        expectedTeacher.changeHomeAddress(this.createAddressStub());

        // WHEN the Teacher is persisted,
        Teacher newTeacher = teacherRepository.save(expectedTeacher);
        assertNotNull(newTeacher.getId());

        // THEN the Teacher should be readable from the database.
        Teacher actualTeacher = teacherRepository
                .getOne(newTeacher.getId()); // Testing our JPA annotations, not repo method
        assertEquals(newTeacher, actualTeacher);
    }

}