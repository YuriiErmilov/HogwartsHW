package ru.hogwarts.school.controller;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private synchronized void printName(Student student) {
        System.out.println(student.getName());
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

    @GetMapping("/names-A")
    public List<String> getStudentsNamesStartsWithA() {
        return studentService.getStudentsNamesStartsWithA();
    }
    @GetMapping("/average-age")
    public double getStudentsAverageAge() {
        return studentService.getStudentsAverageAge();
    }

    @GetMapping("/print-parallel")

    public void printParallelStudents() {
        List<Student> students = studentService.getAllStudents();
        if(students.size() < 6) {
            return;
        }
        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        Thread thread = new Thread(()->{
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        });
        Thread thread1 = new Thread(()->{
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        });
        thread.start();
        thread1.start();

    }

    @GetMapping("/print-synchronized")

    public void printSynchronizedStudents() {
        List<Student> students = studentService.getAllStudents();
        if(students.size() < 6) {
            return;
        }
        printName(students.get(0));
        printName(students.get(1));

        Thread thread = new Thread(()->{
            printName(students.get(2));
            printName(students.get(3));
        });

        Thread thread1 = new Thread(()->{
            printName(students.get(4));
            printName(students.get(5));
        });
        thread.start();
        thread1.start();
    }
}
