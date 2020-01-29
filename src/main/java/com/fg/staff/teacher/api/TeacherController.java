package com.fg.staff.teacher.api;

import com.fg.staff.teacher.api.contract.ChangeAddressCmd;
import com.fg.staff.teacher.api.contract.CreateTeacherCmd;
import com.fg.staff.teacher.api.contract.CreateTeacherCmdResp;
import com.fg.staff.teacher.api.contract.TeacherData;
import com.fg.staff.teacher.app.TeacherService;
import com.fg.staff.teacher.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.*;

@RestController // Spring annotation indicating a REST controller component.
@RequiredArgsConstructor // Lombok annotation that automatically creates a constructor including all "final" properties.
public class TeacherController {

    // ------------------------------------------------- DEPENDENCIES --------------------------------------------------

    private final TeacherService teacherService; // Injected (set) by Spring via the required args constructor.
    private final TeacherTranslator teacherTranslator; // Injected (set) by Spring via the required args constructor.


    // ------------------------------------------------ PUBLIC METHODS -------------------------------------------------

    /**
     * Create a Teacher entity based on the content of the given contract.
     *
     * @param createTeacherCmd the contract containing the data from which to create the Teacher.
     * @return a JSON response containing a representation of the newly created Teacher.
     */
    @PostMapping(path = "/v1/teachers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateTeacherCmdResp> createTeacher(@Valid @RequestBody CreateTeacherCmd createTeacherCmd) {

        // Create the Teacher
        Teacher teacher = teacherService.createTeacher(createTeacherCmd.getName());

        // HATEOAS back to the teacher URI
        CreateTeacherCmdResp response = new CreateTeacherCmdResp();
        response.add(
                linkTo(methodOn(TeacherController.class).getTeacherById(teacher.getId().toString())).withSelfRel());

        // Return 201-Created
        return status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get a Teacher by its ID.
     *
     * @param teacherId the ID of the Teacher to return.
     * @return a JSON response containing a representation of the Teacher with the given ID.
     */
    @GetMapping(path = "/v1/teachers/{teacherId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherData> getTeacherById(@PathVariable("teacherId") String teacherId) {

        // Get the Teacher. Returning a 404-NotFound if it does not exist.
        Teacher teacher = teacherService.getById(UUID.fromString(teacherId));
        if (teacher == null) {
            return notFound().build();
        }

        // Translate to a contract
        TeacherData teacherData = teacherTranslator.translate(teacher);

        // HATEOAS back to the teacher URI
        teacherData.add(linkTo(methodOn(TeacherController.class).getTeacherById(teacherId)).withSelfRel());

        // Return 200-OK
        return ok().body(teacherData);
    }

    /**
     * Change a teacher's home address.
     *
     * @param teacherId        the ID of the Teacher to change.
     * @param changeAddressCmd the command containing the address data.
     * @return a JSON response containing a representation of the Teacher with the given ID.
     */
    @PutMapping(path = "/v1/teachers/{teacherId}/homeAddress", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherData> changeHomeAddress(@PathVariable("teacherId") String teacherId,
                                                         @Valid @RequestBody ChangeAddressCmd changeAddressCmd) {

        // Change the address
        UUID id = UUID.fromString(teacherId);
        Teacher teacher = teacherService
                .changeHomeAddress(id, changeAddressCmd.getStreetName(), changeAddressCmd.getCity(),
                                   changeAddressCmd.getState(), changeAddressCmd.getPostalCode());

        // Returning a 404-NotFound if the teacher does not exist.
        if (teacher == null) {
            return notFound().build();
        }

        // Translate to a contract
        TeacherData teacherData = teacherTranslator.translate(teacher);

        // HATEOAS back to the teacher URI
        teacherData.add(linkTo(methodOn(TeacherController.class).getTeacherById(teacherId)).withSelfRel());

        // Return 200-OK
        return ok().body(teacherData);
    }
}
