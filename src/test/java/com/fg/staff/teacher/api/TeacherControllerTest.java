package com.fg.staff.teacher.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fg.staff.Application;
import com.fg.staff.teacher.api.contract.ChangeAddressCmd;
import com.fg.staff.teacher.api.contract.CreateTeacherCmd;
import com.fg.staff.teacher.api.contract.TeacherData;
import com.fg.staff.teacher.app.TeacherService;
import com.fg.staff.teacher.model.Teacher;
import com.fg.staff.teacher.test.BaseTeacherTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class})
class TeacherControllerTest extends BaseTeacherTest {

    // ------------------------------------------------- DEPENDENCIES --------------------------------------------------

    @Mock
    private TeacherService teacherServiceMock;

    @Spy
    private TeacherTranslator teacherTranslatorMock;


    // ----------------------------------------------- MEMBER VARIABLES ------------------------------------------------

    // Serialization/Deserialization
    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    // Mocks web service functionality
    private MockMvc mockMvc;

    // Class under test
    private TeacherController teacherControllerSpy;


    // ------------------------------------------------- TEST METHODS --------------------------------------------------

    @BeforeEach
    public void setup() {
        teacherControllerSpy = spy(new TeacherController(teacherServiceMock, teacherTranslatorMock));
        mockMvc = MockMvcBuilders.standaloneSetup(teacherControllerSpy).build();
    }

    /**
     * GIVEN a command to create a new teacher, WHEN the service API is called to create the teacher, THEN the HTTP
     * status of the response should be 201-Created AND a teacher should have been created AND the response should be
     * returned with a link to the new teacher.
     */
    @Test
    void createTeacher() throws Exception {

        // Stubs
        Teacher teacherStub = this.createTeacherStub();

        // GIVEN a command to create a new teacher
        CreateTeacherCmd createTeacherCmd = CreateTeacherCmd.builder().name(teacherStub.getName()).build();
        String requestJson = objectMapper.writeValueAsString(createTeacherCmd);

        // Dependency Mocks
        doReturn(teacherStub).when(teacherServiceMock).createTeacher(createTeacherCmd.getName());

        // WHEN the service API is called to create the teacher
        // THEN the HTTP status of the response should be 201-Created
        MvcResult result = mockMvc
                .perform(post("/v1/teachers").content(requestJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andReturn();

        // AND a teacher should have been created
        verify(teacherServiceMock).createTeacher(createTeacherCmd.getName());

        // AND the response should be returned with a link to the new teacher.
        assertTrue(result.getResponse().getContentAsString().contains("/v1/teachers/" + teacherStub.getId()));
    }

    /**
     * GIVEN the ID of the teacher, WHEN the service API is called to return the Teacher, THEN the HTTP stats of the
     * response should be 200-OK AND the application service should have been called to get the Teacher AND the complete
     * data about the Teacher should be returned.
     */
    @Test
    void getTeacherById_Found() throws Exception {

        // Stubs
        Teacher teacherStub = this.createTeacherStub();

        // GIVEN the ID of the teacher
        UUID teacherId = teacherStub.getId();

        // Dependency Mocks
        doReturn(teacherStub).when(teacherServiceMock).getById(teacherId);

        // WHEN the service API is called to return the Teacher
        // THEN the HTTP stats of the response should be 200-OK
        MvcResult result = mockMvc.perform(get("/v1/teachers/" + teacherId)).andExpect(status().isOk()).andReturn();

        // AND the application service should have been called to get the Teacher
        verify(teacherServiceMock).getById(teacherId);

        // AND the complete data about the Teacher should be returned.
        TeacherData teacherData = objectMapper.readValue(result.getResponse().getContentAsString(), TeacherData.class);
        assertEquals(teacherStub.getId().toString(), teacherData.getId());
        // (Full translation tested by TeacherTranslatorTest)
    }

    /**
     * GIVEN an unknown teacher ID, WHEN the service API is called to return the Teacher, THEN the HTTP stats of the
     * response should be 404-NOT FOUND AND the application service should have been called to get the Teacher AND an
     * empty response should be returned.
     */
    @Test
    void getTeacherById_NotFound() throws Exception {

        // GIVEN an unknown teacher ID
        UUID teacherId = UUID.randomUUID();

        // Dependency Mocks
        doReturn(null).when(teacherServiceMock).getById(teacherId);

        // WHEN the service API is called to return the Teacher
        // THEN the HTTP stats of the response should be 404-NOT FOUND
        MvcResult result = mockMvc.perform(get("/v1/teachers/" + teacherId)).andExpect(status().isNotFound())
                .andReturn();

        // AND the application service should have been called to get the Teacher
        verify(teacherServiceMock).getById(teacherId);

        // AND an empty response should be returned.
        assertEquals(0, result.getResponse().getContentAsString().length());
    }

    /**
     * GIVEN the ID of the teacher AND a command to change the Teacher's home address, WHEN the service API is called
     * with the command, THEN the HTTP stats of the response should be 200-OK AND the application service should have
     * been called to change the Teacher's address AND the complete data about the Teacher should be returned.
     */
    @Test
    void changeHomeAddress() throws Exception {

        // Stubs
        Teacher teacherStub = this.createTeacherStub();

        // GIVEN the ID of the teacher
        UUID teacherId = teacherStub.getId();

        // AND a command to change the Teacher's home address
        ChangeAddressCmd changeAddressCmd = this.createChangeAddressCmd();
        String requestJson = objectMapper.writeValueAsString(changeAddressCmd);

        // Mock
        doReturn(teacherStub).when(teacherServiceMock)
                .changeHomeAddress(teacherId, changeAddressCmd.getStreetName(), changeAddressCmd.getCity(),
                                   changeAddressCmd.getState(), changeAddressCmd.getPostalCode());

        // WHEN the service API is called with the command
        // THEN the HTTP stats of the response should be 200-OK
        MvcResult result = mockMvc.perform(
                put("/v1/teachers/" + teacherId.toString() + "/homeAddress").content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        // AND the application service should have been called to change the Teacher's address
        verify(teacherServiceMock)
                .changeHomeAddress(teacherId, changeAddressCmd.getStreetName(), changeAddressCmd.getCity(),
                                   changeAddressCmd.getState(), changeAddressCmd.getPostalCode());

        // AND the complete data about the Teacher should be returned.
        TeacherData teacherData = objectMapper.readValue(result.getResponse().getContentAsString(), TeacherData.class);
        assertEquals(teacherStub.getId().toString(), teacherData.getId());
        // (Full translation tested by TeacherTranslatorTest)
    }

    /**
     * GIVEN an unknown teacher ID AND a command to change the Teacher's home address, WHEN the service API is called to
     * return the Teacher, THEN the HTTP stats of the response should be 404-NOT FOUND AND the application service
     * should have been called to get the Teacher AND an empty response should be returned.
     */
    @Test
    void changeHomeAddress_NotFound() throws Exception {

        // GIVEN an unknown teacher ID
        UUID teacherId = UUID.randomUUID();

        // AND a command to change the Teacher's home address
        ChangeAddressCmd changeAddressCmd = this.createChangeAddressCmd();
        String requestJson = objectMapper.writeValueAsString(changeAddressCmd);

        // Dependency Mocks
        doReturn(null).when(teacherServiceMock)
                .changeHomeAddress(teacherId, changeAddressCmd.getStreetName(), changeAddressCmd.getCity(),
                                   changeAddressCmd.getState(), changeAddressCmd.getPostalCode());


        // WHEN the service API is called with the command
        // THEN the HTTP stats of the response should be 404-NOT FOUND
        MvcResult result = mockMvc.perform(
                put("/v1/teachers/" + teacherId.toString() + "/homeAddress").content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn();

        // AND the application service should have been called to get the Teacher
        verify(teacherServiceMock)
                .changeHomeAddress(teacherId, changeAddressCmd.getStreetName(), changeAddressCmd.getCity(),
                                   changeAddressCmd.getState(), changeAddressCmd.getPostalCode());

        // AND an empty response should be returned.
        assertEquals(0, result.getResponse().getContentAsString().length());
    }

}