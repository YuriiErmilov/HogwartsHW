package ru.hogwarts.school.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.LongStream;

@Service
public class FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> findByNameOrColor (String query){
        logger.info("findByNameOrColor");
        if(query == null || query.isBlank()){
            logger.error("query is null or query is blank");
            throw new EntityNotFoundException("Faculty name or color parameter is invalid");
        }
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(query, query);
    }

    public List<Faculty> getAllFaculties() {
        logger.info("getAllFaculties");
        return facultyRepository.findAll();
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("createFaculty");
       return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        logger.info("findFaculty");
        return facultyRepository.findById(id).orElseThrow(()-> {
            logger.error("faculty with id {} not found", id);
            return new EntityNotFoundException("Faculty not found");
        });
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("editFaculty");
        if (faculty.getId() == null || !facultyRepository.existsById(faculty.getId()))  {
            logger.error("faculty with id {} not found", faculty.getId());
            throw new EntityNotFoundException("Faculty with id " + faculty.getId() + " not found");
        }
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.info("deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public List<Faculty> findFacultyByColor(String color) {
        logger.info("findFacultyByColor");
        if (color == null || color.isBlank()) {
            logger.error("color is empty");
            throw new IllegalArgumentException("color is required");
        }
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public String getLongestName() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public long calculateSum() {
        return LongStream.rangeClosed(1, 1_000_000)
                .parallel()
                .sum();
    }
}
