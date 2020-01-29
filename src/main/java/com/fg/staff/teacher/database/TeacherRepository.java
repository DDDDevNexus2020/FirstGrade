package com.fg.staff.teacher.database;

import com.fg.staff.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository // Spring annotation indicating a "repository" component. A singleton that can be injected as a dependency.
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    // Note that this is an interface! Spring and JPA do the work of dynamically creating the concrete class for you.
    // JPA magic!
    Teacher findByName(String name);
}
