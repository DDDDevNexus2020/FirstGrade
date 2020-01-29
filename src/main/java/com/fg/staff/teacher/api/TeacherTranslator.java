package com.fg.staff.teacher.api;

import com.fg.staff.teacher.api.contract.TeacherData;
import com.fg.staff.teacher.model.Address;
import com.fg.staff.teacher.model.Teacher;
import lombok.NonNull;
import org.springframework.stereotype.Service;

// This class is part of the "anti-corruption layer" that translates API contracts from/to domain objects.
@Service // Spring annotation indicating a "service" component. A singleton that can be injected as a dependency.
class TeacherTranslator { // Note that this class is package-protected. Only the "api" layer needs this class.

    // ------------------------------------------------ PUBLIC METHODS -------------------------------------------------

    /**
     * Translate the given Teacher entity into an API contract.
     *
     * @param teacher the Teacher entity to translate.
     * @return a contract representing the Teacher entity.
     */
    TeacherData translate(@NonNull Teacher teacher) { // @NonNull creates a NullPointerException if a null argument.
        Address teacherAddress = teacher.getHomeAddress();
        return TeacherData.builder() //
                .id(teacher.getId().toString()) //
                .name(teacher.getName()) //
                .homeStreetName(teacherAddress == null ? null : teacherAddress.getStreetName()) //
                .homeCity(teacherAddress == null ? null : teacherAddress.getCity()) //
                .homeState(teacherAddress == null ? null : teacherAddress.getState()) //
                .homePostalCode(teacherAddress == null ? null : teacherAddress.getPostalCode()) //
                .build();
    }
}
