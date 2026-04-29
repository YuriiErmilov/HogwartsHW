package ru.hogwarts.school.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findStudentsByAgeBetween(int min, int max) {
        if(min > max) {
            throw new IllegalArgumentException("min > max");
        }
        return studentRepository.findByAgeBetween(min,max);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent (Student student) {
        return studentRepository.save(student) ;
    }

    public Student findStudent (Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Student not found"));
    }

    public Student editStudent (Student student) {
        if (student.getId() == null || !studentRepository.existsById(student.getId())) {
            throw new EntityNotFoundException("Student with id " + student.getId() + " not found");
        }
        return studentRepository.save(student);
    }

    public void deleteStudent (Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsByAge (int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        List<Student> result = studentRepository.findByAge(age);
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Student with age " + age + " not found");
        }
        return result;
    }
}
