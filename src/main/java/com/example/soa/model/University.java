package com.example.soa.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "University")
public class University {
    private List<Student> students;

    // Default no-arg constructor
    public University() {
    }

    // Getter and Setter
    @XmlElement(name = "Student") // Maps the Student element in the list
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
