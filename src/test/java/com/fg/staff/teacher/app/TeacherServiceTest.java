package com.fg.staff.teacher.app;

import com.fg.staff.teacher.infrastructure.TeacherRepository;
import com.fg.staff.teacher.model.Address;
import com.fg.staff.teacher.model.Teacher;
import com.fg.staff.teacher.test.BaseTeacherTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest extends BaseTeacherTest {

    // ------------------------------------------------- DEPENDENCIES --------------------------------------------------

    @Mock
    private TeacherRepository teacherRepositoryMock;


    // ----------------------------------------------- MEMBER VARIABLES ------------------------------------------------

    // Class under test
    @Spy
    @InjectMocks
    private TeacherService teacherServiceSpy;


    // ------------------------------------------------- TEST METHODS --------------------------------------------------

    /**
     * GIVEN the name of a Teacher, WHEN the Teacher is created, THEN the Teacher should be persisted AND returned.
     */
    @Test
    void createTeacher() {
        // GIVEN the name of a Teacher,
        String name = podamFactory.manufacturePojo(String.class);

        // Dependency Mocks
        Teacher teacherStub = new Teacher(name);
        doReturn(teacherStub).when(teacherRepositoryMock).save(any(Teacher.class));

        // WHEN the Teacher is created,
        Teacher teacher = teacherServiceSpy.createTeacher(name);

        // THEN the Teacher should be persisted
        verify(teacherRepositoryMock).save(any(Teacher.class));

        // AND returned.
        assertEquals(teacherStub, teacher);
    }

    /**
     * GIVEN the ID of a Teacher, WHEN the Teacher is requested, THEN the Teacher should requested from the repository
     * AND returned.
     */
    @Test
    void getById() {

        // GIVEN the ID of a Teacher
        UUID teacherId = UUID.randomUUID();

        // Dependency Mocks
        Teacher teacherStub = this.createTeacherStub();
        Optional<Teacher> optional = Optional.of(teacherStub);
        doReturn(optional).when(teacherRepositoryMock).findById(teacherId);

        // WHEN the Teacher is requested
        Teacher teacher = teacherServiceSpy.getById(teacherId);

        // THEN the Teacher should requested from the repository
        verify(teacherRepositoryMock).findById(teacherId);

        // AND returned.
        assertEquals(teacherStub, teacher);
    }

    /**
     * GIVEN the ID of a Teacher AND the fields of an address, WHEN the Teacher's home address is changed, THEN the
     * Teacher should requested from the repository AND the teacher's home address changed AND the Teacher should be
     * persisted AND returned.
     */
    @Test
    void changeHomeAddress() {

        // Stubs
        Teacher teacherStub = this.createTeacherStub();

        // GIVEN the ID of a Teacher
        UUID teacherId = teacherStub.getId();

        // AND the fields of an address
        Address address = this.createAddressStub();

        // Dependency Mocks
        Optional<Teacher> optional = Optional.of(teacherStub);
        doReturn(optional).when(teacherRepositoryMock).findById(teacherId);
        doReturn(teacherStub).when(teacherRepositoryMock).save(teacherStub);

        // WHEN the Teacher's home address is changed
        Teacher teacher = teacherServiceSpy
                .changeHomeAddress(teacherId, address.getStreetName(), address.getCity(), address.getState(),
                                   address.getPostalCode());

        // THEN the Teacher should requested from the repository
        verify(teacherRepositoryMock).findById(teacherId);

        // AND the teacher's home address changed
        assertEquals(address, teacher.getHomeAddress());

        // AND the Teacher should be persisted
        verify(teacherRepositoryMock).save(teacherStub);

        // AND returned.
        assertEquals(teacherStub.getHomeAddress(), teacher.getHomeAddress());
    }

    /**
     * GIVEN an invalid ID for a Teacher AND the fields of an address, WHEN the attempt is made to change the Teacher's
     * home address, THEN the Teacher should requested from the repository AND null should be returned.
     */
    @Test
    void changeHomeAddress_notFound() {


        // GIVEN an invalid ID for a Teacher
        UUID teacherId = UUID.randomUUID();

        // AND the fields of an address
        Address address = this.createAddressStub();

        // Dependency Mocks
        Optional<Teacher> optional = Optional.empty();
        doReturn(optional).when(teacherRepositoryMock).findById(teacherId);

        // WHEN the attempt is made to change the Teacher's home address
        Teacher teacher = teacherServiceSpy
                .changeHomeAddress(teacherId, address.getStreetName(), address.getCity(), address.getState(),
                                   address.getPostalCode());

        // THEN the Teacher should requested from the repository
        verify(teacherRepositoryMock).findById(teacherId);

        // AND null should be returned.
        assertNull(teacher);
    }
}