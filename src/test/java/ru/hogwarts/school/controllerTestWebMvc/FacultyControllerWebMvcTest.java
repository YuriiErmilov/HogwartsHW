package ru.hogwarts.school.controllerTestWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllFaculties() throws Exception {
        when(facultyService.getAllFaculties())
                .thenReturn(List.of(new Faculty()));

        mockMvc.perform(get("/faculties/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("Red");

        when(facultyService.createFaculty(any()))
                .thenReturn(faculty);

        mockMvc.perform(post("/faculties")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetFacultyById() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);

        when(facultyService.findFaculty(1L))
                .thenReturn(faculty);

        mockMvc.perform(get("/faculties/by-id/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchFaculty() throws Exception {
        when(facultyService.findByNameOrColor("Red"))
                .thenReturn(List.of(new Faculty()));

        mockMvc.perform(get("/faculties/search?query=Red"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByColor() throws Exception {
        when(facultyService.findFacultyByColor("Blue"))
                .thenReturn(List.of(new Faculty()));

        mockMvc.perform(get("/faculties/by-color?color=Blue"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetFacultyStudents() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setStudents(List.of(new Student()));

        when(facultyService.findFaculty(1L))
                .thenReturn(faculty);

        mockMvc.perform(get("/faculties/1/students"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);

        when(facultyService.editFaculty(any()))
                .thenReturn(faculty);

        mockMvc.perform(put("/faculties")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteFaculty() throws Exception {
        mockMvc.perform(delete("/faculties/1"))
                .andExpect(status().isOk());
    }
}
