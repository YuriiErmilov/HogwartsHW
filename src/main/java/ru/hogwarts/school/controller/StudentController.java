package ru.hogwarts.school.controller;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}/faculty")
    public Faculty getStudentFaculty(@PathVariable Long id) {
        return studentService.findStudent(id).getFaculty();
    }

    @GetMapping("/by-age-between")
    public List<Student> getStudentsByAgeBetween (@RequestParam int min,@RequestParam int max) {
        return studentService.findStudentsByAgeBetween(min,max);
    }

    @GetMapping("/by-id/{id}")
    public Student getStudent(@PathVariable long id) {
        return studentService.findStudent(id);
    }

    @PostMapping()
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping()
    public Student updateStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
    }
    @GetMapping("/by-age")
    public List<Student> getStudentsByAge (@RequestParam int age) {
        return studentService.findStudentsByAge(age);
    }

    @GetMapping("/all")
    public List<Student> getALLStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/count")
    public long countStudents() {
        return studentService.getStudentsCount();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudents() {
        return studentService.findLastFiveStudents();
    }
}
