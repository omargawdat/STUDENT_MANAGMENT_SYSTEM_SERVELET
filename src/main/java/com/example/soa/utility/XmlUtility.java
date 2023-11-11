package com.example.soa.utility;

import com.example.soa.model.Student;
import com.example.soa.model.University;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "Utility")
public class XmlUtility {

    private static final String FILE_NAME = "university.xml";

    // Method to marshal University object to XML
    public static void marshalToFile(University university) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(University.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(university, new File(FILE_NAME));
    }

    // Method to unmarshal XML content to University object
    public static University unmarshalFromFile() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(University.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (University) unmarshaller.unmarshal(new File(FILE_NAME));
    }

    // Method to add a Student to the University
    public static void addStudent(Student student) throws JAXBException {
        University university = new University();
        File file = new File(FILE_NAME);

        // Check if file exists and has content to add to existing data
        if (file.exists() && file.length() > 0) {
            university = unmarshalFromFile();
        }

        List<Student> students = university.getStudents();
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        university.setStudents(students);
        marshalToFile(university);
    }

    // Method to find students by GPA or FirstName
    public static List<Student> findStudents(String firstName, Double gpa) throws JAXBException {
        University university = unmarshalFromFile();
        List<Student> matchedStudents = new ArrayList<>();

        // Filter by GPA or FirstName
        if (university.getStudents() != null) {
            matchedStudents = university.getStudents().stream()
                    .filter(s -> (firstName == null || s.getFirstName().equals(firstName)) &&
                            (gpa == null || s.getGpa() == gpa))
                    .collect(Collectors.toList());
        }

        return matchedStudents;
    }

    // Method to delete a student by ID
    public static void deleteStudent(String studentId) throws JAXBException {
        University university = unmarshalFromFile();

        if (university.getStudents() != null) {
            List<Student> updatedStudents = university.getStudents().stream()
                    .filter(s -> !s.getId().equals(studentId))
                    .collect(Collectors.toList());

            university.setStudents(updatedStudents);
            marshalToFile(university);
        }
    }
}
