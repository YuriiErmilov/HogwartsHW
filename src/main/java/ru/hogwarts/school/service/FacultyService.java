package ru.hogwarts.school.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> findByNameOrColor (String query){
        if(query == null || query.isBlank()){
            throw new EntityNotFoundException("Faculty name or color parameter is invalid");
        }
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(query, query);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty createFaculty(Faculty faculty) {
       return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Faculty not found"));
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculty.getId() == null || !facultyRepository.existsById(faculty.getId()))  {
            throw new EntityNotFoundException("Faculty with id " + faculty.getId() + " not found");
        }
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> findFacultyByColor(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("color is required");
        }
        return facultyRepository.findByColorIgnoreCase(color);
    }
}
