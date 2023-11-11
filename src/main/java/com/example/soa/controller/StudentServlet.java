package com.example.soa.controller;

import com.example.soa.model.Student;
import com.example.soa.model.University;
import com.example.soa.utility.XMLUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "StudentServlet", urlPatterns = {"/students"})
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // default action
        }

        switch (action) {
            case "add":
                int numToAdd = 1; // Default number of students to add
                try {
                    // Parse the number of students to add from the request parameter
                    String numStudentsParam = request.getParameter("numStudents");
                    if (numStudentsParam != null && !numStudentsParam.isEmpty()) {
                        numToAdd = Integer.parseInt(numStudentsParam);
                    }
                } catch (NumberFormatException e) {
                    // Log error or handle the number format exception
                    numToAdd = 1; // Reset to default if parsing fails
                }
                request.setAttribute("numStudents", numToAdd);
                request.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(request, response);
                break;
            case "searchByName":
                // Search students by first name
                String firstName = request.getParameter("firstName");
                Student student = XMLUtility.searchStudentByFirstName(firstName);
                request.setAttribute("students", student != null ? List.of(student) : List.of());
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
                break;
            case "filterByGpa":
                // Filter students by GPA
                double gpa = parseGpa(request.getParameter("gpa"));
                List<Student> filteredStudents = XMLUtility.searchStudentByGPA(gpa);
                request.setAttribute("students", filteredStudents);
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
                break;
            case "list":
            default:
                // List all students
                List<Student> students = XMLUtility.getAllStudents();
                request.setAttribute("students", students);
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
                break;
        }

    }

    private double parseGpa(String gpaStr) {
        try {
            return Double.parseDouble(gpaStr);
        } catch (NumberFormatException e) {
            // Handle parsing error if necessary, perhaps logging or setting an attribute for the request
            return -1; // Returning -1 or any invalid value to indicate the error, adjust as necessary
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve action parameter to check what action to perform
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Retrieve all student-related parameters
            String[] firstNames = request.getParameterValues("firstName");
            String[] gpas = request.getParameterValues("gpa");
            String[] genders = request.getParameterValues("gender");
            String[] addresses = request.getParameterValues("address");
            String[] levels = request.getParameterValues("level");

            for (int i = 0; i < firstNames.length; i++) {
                try {
                    // Generate a new unique ID for the student
                    String newId = XMLUtility.generateNewStudentId(); // This method needs to be implemented to handle ID generation

                    double gpa = Double.parseDouble(gpas[i]);
                    Student student = new Student(firstNames[i], genders[i], levels[i], addresses[i], gpa);
                    student.setId(newId); // Set the newly generated ID
                    XMLUtility.addStudentToUniversity(student); // Add student to XML storage
                } catch (NumberFormatException e) {
                    // Handle the individual parsing error for each student
                    // Redirect to an error page or show error message
                }
            }
            response.sendRedirect("students");
        } else if ("delete".equals(action)) {
            // Delete student action
            String studentId = request.getParameter("studentId");
            // print the student id with wtf
            System.out.println("WTF Deleting student with ID: " + studentId); // Logging the ID for debugging
            XMLUtility.deleteStudentById(studentId);
            response.sendRedirect("students");
        }
    }
}