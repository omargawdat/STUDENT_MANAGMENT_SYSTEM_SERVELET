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

    public static boolean doesStudentIdExist(String studentId) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            return university.getStudents().stream()
                    .anyMatch(student -> student.getId().equals(studentId));
        }
        return false;
    }

    public static Student getStudentById(String studentId) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            return university.getStudents().stream()
                    .filter(student -> student.getId().equals(studentId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public static void updateStudent(Student studentToUpdate) {
        University university = unmarshalUniversity();

        if (university != null && university.getStudents() != null) {
            List<Student> students = university.getStudents();
            for (Student existingStudent : students) {
                if (existingStudent.getId().equals(studentToUpdate.getId())) {
                    existingStudent.setFirstName(studentToUpdate.getFirstName());
                    existingStudent.setGender(studentToUpdate.getGender());
                    existingStudent.setLevel(studentToUpdate.getLevel());
                    existingStudent.setAddress(studentToUpdate.getAddress());
                    existingStudent.setGpa(studentToUpdate.getGpa());
                    break;
                }
            }
            marshalUniversity(university);
        }
    }

    public static List<Student> searchStudentById(String id) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            return university.getStudents().stream()
                    .filter(student -> student.getId().equals(id))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


    public static List<Student> searchStudentByGender(String gender) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            return university.getStudents().stream()
                    .filter(student -> student.getGender().equalsIgnoreCase(gender))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static List<Student> searchStudentByLevel(String level) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            return university.getStudents().stream()
                    .filter(student -> student.getLevel().equalsIgnoreCase(level))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static List<Student> searchStudentByAddress(String address) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            return university.getStudents().stream()
                    .filter(student -> student.getAddress().equalsIgnoreCase(address))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static List<Student> sortStudentsByName(String order) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            List<Student> sortedStudents = university.getStudents().stream()
                    .sorted((s1, s2) -> "asc".equals(order) ? s1.getFirstName().compareTo(s2.getFirstName()) : s2.getFirstName().compareTo(s1.getFirstName()))
                    .collect(Collectors.toList());
            university.setStudents(sortedStudents); // Update the students list in the University object
            marshalUniversity(university); // Write the updated University object back to the XML file
            return sortedStudents;
        }
        return new ArrayList<>();
    }

    public static List<Student> sortStudentsById(String order) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            List<Student> sortedStudents = university.getStudents().stream()
                    .sorted((s1, s2) -> "asc".equals(order) ? s1.getId().compareTo(s2.getId()) : s2.getId().compareTo(s1.getId()))
                    .collect(Collectors.toList());
            university.setStudents(sortedStudents);
            marshalUniversity(university);
            return sortedStudents;
        }
        return new ArrayList<>();
    }

    public static List<Student> sortStudentsByGender(String order) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            List<Student> sortedStudents = university.getStudents().stream()
                    .sorted((s1, s2) -> "asc".equals(order) ? s1.getGender().compareTo(s2.getGender()) : s2.getGender().compareTo(s1.getGender()))
                    .collect(Collectors.toList());
            university.setStudents(sortedStudents);
            marshalUniversity(university);
            return sortedStudents;
        }
        return new ArrayList<>();
    }

    public static List<Student> sortStudentsByLevel(String order) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            List<Student> sortedStudents = university.getStudents().stream()
                    .sorted((s1, s2) -> "asc".equals(order) ? s1.getLevel().compareTo(s2.getLevel()) : s2.getLevel().compareTo(s1.getLevel()))
                    .collect(Collectors.toList());
            university.setStudents(sortedStudents);
            marshalUniversity(university);
            return sortedStudents;
        }
        return new ArrayList<>();
    }

    public static List<Student> sortStudentsByAddress(String order) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            List<Student> sortedStudents = university.getStudents().stream()
                    .sorted((s1, s2) -> "asc".equals(order) ? s1.getAddress().compareTo(s2.getAddress()) : s2.getAddress().compareTo(s1.getAddress()))
                    .collect(Collectors.toList());
            university.setStudents(sortedStudents);
            marshalUniversity(university);
            return sortedStudents;
        }
        return new ArrayList<>();
    }

    public static List<Student> sortStudentsByGpa(String order) {
        University university = unmarshalUniversity();
        if (university != null && university.getStudents() != null) {
            List<Student> sortedStudents = university.getStudents().stream()
                    .sorted((s1, s2) -> "asc".equals(order) ? Double.compare(s1.getGpa(), s2.getGpa()) : Double.compare(s2.getGpa(), s1.getGpa()))
                    .collect(Collectors.toList());
            university.setStudents(sortedStudents);
            marshalUniversity(university);
            return sortedStudents;
        }
        return new ArrayList<>();
    }
}
