package ru.hogwarts.school.controllerTest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url(String path){
       return "http://localhost:" + port + "/faculty" + path;
    }

    @Test
    void testCreateFaculty(){
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("Red");

        Faculty response = restTemplate.postForObject(url(""), faculty, Faculty.class);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isNotNull();
    }

    @Test
    void testGetAllFaculty(){
        Faculty[] faculties = restTemplate.getForObject(url("/all"), Faculty[].class);
        assertThat(faculties).isNotNull();
    }

    @Test
    void tesdtSearchFaculty(){
        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("Green");

        restTemplate.postForObject(url(""), faculty, Faculty.class);

        Faculty[] result = restTemplate.getForObject(url("/search?query-Green"), Faculty[].class);

        assertThat(result.length).isGreaterThan(0);
    }
}
