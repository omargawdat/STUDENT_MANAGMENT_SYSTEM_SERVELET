package com.example.soa.utility;

import com.example.soa.model.University;
import com.example.soa.model.Student;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.util.List;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class XMLUtility {
    private static final String FILE_PATH = "/Users/omar/Main/Projects/Dummy/Java/SOA_ASSIG_JSP_12/SOA/src/main/university.xml";

    public static void marshalUniversity(University university) {
        try {
            JAXBContext context = JAXBContext.newInstance(University.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to File
            marshaller.marshal(university, new FileWriter(FILE_PATH));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public static University unmarshalUniversity() {
        File file = new File(FILE_PATH);
        // Check if the file exists and is not empty
        if (!file.exists() || file.length() == 0) {
            // Either file doesn't exist or is empty, so return a new University instance
            return new University();
        } else {
            try {
                JAXBContext context = JAXBContext.newInstance(University.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                // Read from file
                return (University) unmarshaller.unmarshal(new FileReader(file));
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
                return null; // or handle differently
            }
        }
    }

    public static void addStudentToUniversity(Student studentToAdd) {
        University university = unmarshalUniversity(); // Load the current university data

        // Check if the students list is null and initialize it if necessary
        if (university.getStudents() == null) {
            university.setStudents(new ArrayList<Student>());
        }

        university.getStudents().add(studentToAdd); // Add the new student to the list
        marshalUniversity(university); // Save the updated university object to XML
    }

    public static void deleteStudentById(String studentId) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            university.setStudents(university.getStudents().stream()
                    .filter(student -> !student.getId().equals(studentId))
                    .collect(Collectors.toList()));
            marshalUniversity(university);
        }
    }

    public static List<Student> searchStudentByFirstName(String firstName) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            return university.getStudents().stream()
                    .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


    public static List<Student> searchStudentByGPA(double gpa) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            return university.getStudents().stream()
                    .filter(student -> student.getGpa() == gpa)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static List<Student> getAllStudents() {
        University university = unmarshalUniversity();
        return university != null ? university.getStudents() : new ArrayList<>();
    }

}
