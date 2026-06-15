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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findStudentsByAgeBetween(int min, int max) {
        logger.info("findStudentsByAgeBetween");
        if(min > max) {
            logger.info("Min age is greater than max age");
            throw new IllegalArgumentException("min > max");
        }
        return studentRepository.findByAgeBetween(min,max);
    }

    public List<Student> getAllStudents() {
        logger.info("getAllStudents");
        return studentRepository.findAll();
    }

    public Student createStudent (Student student) {
        logger.info("Was created student");
        logger.debug("Creating student with name = {}", student.getName());
        return studentRepository.save(student) ;
    }

    public Student findStudent (Long id) {
        logger.info("Was invoked method find student");
        return studentRepository.findById(id)
                .orElseThrow(()-> {
                    logger.error("Student not found with id = {} ", id);

                    return new EntityNotFoundException("Student not found");
                });
    }

    public Student editStudent (Student student) {
        logger.info("Was invoked method edit student");
        if (student.getId() == null || !studentRepository.existsById(student.getId())) {
            logger.error("Student not found with id = {} ", student.getId());
            throw new EntityNotFoundException("Student with id " + student.getId() + " not found");
        }
        return studentRepository.save(student);
    }

    public void deleteStudent (Long id) {
        logger.info("Was invoked method delete student");
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsByAge (int age) {
        logger.info("findStudentsByAge");
        if (age < 0) {
            logger.error("Age is less than 0");
            throw new IllegalArgumentException("Age cannot be negative");
        }
        List<Student> result = studentRepository.findByAge(age);
        if (result.isEmpty()) {
            logger.error("Student with age = {} not found  ", age);
            throw new IllegalArgumentException("Student with age " + age + " not found");
        }
        return result;
    }

    public long getStudentsCount() {
        logger.info("getStudentsCount");
        return studentRepository.getStudentCount();
    }

    public double getAverageAge() {
        logger.info("getAverageAge");
        return studentRepository.getAverageAge();
    }

    public List<Student> findLastFiveStudents() {
        logger.info("findLastFiveStudents");
        return studentRepository.findLastFiveStudents();
    }
}
