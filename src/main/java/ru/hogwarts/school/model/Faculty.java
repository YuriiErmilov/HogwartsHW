package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String color;

    @JsonIgnore
    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Faculty() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color + "\'" +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Faculty faculty)) return false;
        return id != null && id.equals(faculty.id);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
