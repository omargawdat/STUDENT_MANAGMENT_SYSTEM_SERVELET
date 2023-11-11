
import com.example.soa.model.Student;
import com.example.soa.model.University;
import com.example.soa.utility.XmlUtility;
import jakarta.xml.bind.JAXBException;

import java.util.List;
import java.util.Scanner;

public class XmlUtilityTest {

    public static void main(String[] args) {
        try {
            // Add a new student
            Student newStudent = new Student();
            newStudent.setId("20200134");
            newStudent.setFirstName("Ahmed");
            newStudent.setLastName("Mohamed");
            newStudent.setGender("Male");
            newStudent.setGpa(3.17);
            newStudent.setLevel(4);
            newStudent.setAddress("Giza");

            XmlUtility.addStudent(newStudent);

            // Print out the university XML content
            University university = XmlUtility.unmarshalFromFile();
            System.out.println("University data after adding a student:");
            System.out.println(university.getStudents());

            // Search for a student by GPA
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter GPA to search for: ");
            double gpa = scanner.nextDouble();
            List<Student> foundStudents = XmlUtility.findStudents(null, gpa);
            System.out.println("Search results for GPA " + gpa + ": " + foundStudents);

            // Delete a student
            System.out.print("Enter student ID to delete: ");
            String idToDelete = scanner.next();
            XmlUtility.deleteStudent(idToDelete);
            university = XmlUtility.unmarshalFromFile();
            System.out.println("University data after deleting a student:");
            System.out.println(university.getStudents());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
