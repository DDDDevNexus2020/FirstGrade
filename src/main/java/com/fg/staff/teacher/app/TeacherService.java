package com.fg.staff.teacher.app;

import com.fg.staff.teacher.infrastructure.TeacherRepository;
import com.fg.staff.teacher.model.Address;
import com.fg.staff.teacher.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service // Spring annotation indicating a "service" component. A singleton that can be injected as a dependency.
@RequiredArgsConstructor // Lombok annotation that automatically creates a constructor including all "final" properties.
public class TeacherService {

    // ------------------------------------------------- DEPENDENCIES --------------------------------------------------

    private final TeacherRepository teacherRepository; // Injected (set) by Spring via the required args constructor.


    // ------------------------------------------------ PUBLIC METHODS -------------------------------------------------

    /**
     * Create and persist a new Teacher entity with the given name and home address.
     *
     * @param name the name of the new Teacher
     * @return the Teacher that was created.
     */
    public Teacher createTeacher(String name) {
        Teacher teacher = new Teacher(name);
        return teacherRepository.save(teacher);
    }

    /**
     * Return the Teacher with the given ID.
     *
     * @param id the ID of the Teacher to return.
     * @return the Teacher with the given ID. If the Teacher can not be found, then null is returned.
     */
    public Teacher getById(UUID id) {
        return teacherRepository.findById(id).orElse(null);
    }

    /**
     * Changes the given Teacher's home address.
     *
     * @return the Teacher, with its address changed.
     */
    public Teacher changeHomeAddress(UUID teacherId, String streetName, String city, String state, String postalCode) {

        // Get the teacher
        Teacher teacher = this.getById(teacherId);
        if (teacher == null) {
            return null;
        }

        // Set the home address
        teacher.changeHomeAddress(Address.builder() //
                                          .streetName(streetName) //
                                          .city(city) //
                                          .state(state) //
                                          .postalCode(postalCode) //
                                          .build());
        // Done
        return teacherRepository.save(teacher);
    }
}
