package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.stream.LongStream;

@RestController
@RequestMapping("faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}/students")

    public List<Student> getFacultyStudents (@PathVariable Long id) {
        return facultyService.findFaculty(id).getStudents();
    }

    @GetMapping("/search")
    public List<Faculty> searchFaculty(@RequestParam String query){
        return facultyService.findByNameOrColor(query);
    }

    @GetMapping("/by-id/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }
    @PostMapping()
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping()
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable Long id) {
       facultyService.deleteFaculty(id);

    }

    @GetMapping("/by-color")
    public List<Faculty> getFacultysByColor(@RequestParam String color) {
        return facultyService.findFacultyByColor(color);
    }

    @GetMapping("/all")
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }


    @GetMapping("/longest-name")
    public String getLongestName() {
        return facultyService.getLongestName();
    }

    @GetMapping("/integer-value")
    public long integerValue() {
        return facultyService.calculateSum();
    }

}
