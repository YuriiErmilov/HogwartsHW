package ru.hogwarts.school.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url(String path) {
        return "http://localhost:" + port + "/students" + path;
    }


    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setName("John");
        student.setAge(17);

        Student response = restTemplate.postForObject(url(""), student, Student.class);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();

    }

    @Test
    void testGetAllStudents() {
        Student[] students = restTemplate.getForObject(url("/all"), Student[].class);
        assertThat(students).isNotNull();

    }

    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setName("Yurii");
        student.setAge(21);

        Student created =   restTemplate.postForObject(url(""), student, Student.class);

        Student found = restTemplate.getForObject(url("/by-id/" + created.getId()), Student.class);

        assertThat(found.getId()).isEqualTo(created.getId());
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student();
        student.setName("Karl");
        student.setAge(15);

        Student created = restTemplate.postForObject(url("/"), student, Student.class);

        restTemplate.delete(url("/" + created.getId()));

        ResponseEntity<Student> response = restTemplate.getForEntity(url("/by-id/" + created.getId()), Student.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }


    @Test
    void testGetByAge() {
        Student student = new Student();
        student.setName("Simson");
        student.setAge(11);

        restTemplate.postForObject(url("/"), student, Student.class);

        Student [] result = restTemplate.getForObject(url("/by-age/" + student.getAge()), Student[].class);

    assertThat(result.length).isGreaterThan(0);
    }
}
