package com.example.soa.model;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;


@XmlRootElement
public class University {
    private List<Student> students;

    // Getters and setters
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
