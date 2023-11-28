package com.example.soa.model;

import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Student {
    private String id;
    private String firstName;
    private String gender;
    private String level;
    private String address;
    private double gpa;

    // Constructors
    public Student() {
    }

    public Student(String id, String firstName, String gender, String level, String address, double gpa) {
        this.id = id;
        this.firstName = firstName;
        this.gender = gender;
        this.level = level;
        this.address = address;
        this.gpa = gpa;
    }

    // set id
    public void setId(String id) {
        this.id = id;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    // No setter for ID since it's auto-generated

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}