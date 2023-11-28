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
                String firstName = request.getParameter("firstName");
                List<Student> studentss = XMLUtility.searchStudentByFirstName(firstName);
                request.setAttribute("students", studentss);
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
            case "filterByGpa":
                // Filter students by GPA
                double gpa = parseGpa(request.getParameter("gpa"));
                List<Student> filteredStudents = XMLUtility.searchStudentByGPA(gpa);
                request.setAttribute("students", filteredStudents);
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
                break;
            case "searchById":
                String id = request.getParameter("id");
                List<Student> studentsById = XMLUtility.searchStudentById(id);
                request.setAttribute("students", studentsById);
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
                break;

            case "searchByGender":
                String gender = request.getParameter("gender");
                List<Student> studentsByGender = XMLUtility.searchStudentByGender(gender);
                request.setAttribute("students", studentsByGender);
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
                break;

            case "searchByLevel":
                String level = request.getParameter("level");
                List<Student> studentsByLevel = XMLUtility.searchStudentByLevel(level);
                request.setAttribute("students", studentsByLevel);
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
                break;

            case "searchByAddress":
                String address = request.getParameter("address");
                List<Student> studentsByAddress = XMLUtility.searchStudentByAddress(address);
                request.setAttribute("students", studentsByAddress);
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
                break;
            case "edit":
                String studentId = request.getParameter("studentId");
                Student studentToEdit = XMLUtility.getStudentById(studentId);
                if (studentToEdit != null) {
                    request.setAttribute("studentToEdit", studentToEdit);
                    request.getRequestDispatcher("/WEB-INF/editStudent.jsp").forward(request, response);
                }
                break;
            case "list":
            default:
                // List all students
                List<Student> students = XMLUtility.getAllStudents();
                request.setAttribute("students", students);
                request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
                break;
            case "sort":
                String field = request.getParameter("field");
                String order = request.getParameter("order");
                List<Student> sortedStudents = null;

                switch (field) {
                    case "id":
                        sortedStudents = XMLUtility.sortStudentsById(order);
                        break;
                    case "firstName":
                        sortedStudents = XMLUtility.sortStudentsByName(order);
                        break;
                    case "gender":
                        sortedStudents = XMLUtility.sortStudentsByGender(order);
                        break;
                    case "level":
                        sortedStudents = XMLUtility.sortStudentsByLevel(order);
                        break;
                    case "address":
                        sortedStudents = XMLUtility.sortStudentsByAddress(order);
                        break;
                    case "gpa":
                        sortedStudents = XMLUtility.sortStudentsByGpa(order);
                        break;
                    default:
                        break;
                }

                if (sortedStudents != null) {
                    request.setAttribute("students", sortedStudents);
                }
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
            String[] ids = request.getParameterValues("id");
            String[] firstNames = request.getParameterValues("firstName");
            String[] gpas = request.getParameterValues("gpa");
            String[] genders = request.getParameterValues("gender");
            String[] addresses = request.getParameterValues("address");
            String[] levels = request.getParameterValues("level");


            for (int i = 0; i < firstNames.length; i++) {
                try {
                    double gpa = Double.parseDouble(gpas[i]);
                    Student student = new Student(ids[i], firstNames[i], genders[i], levels[i], addresses[i], gpa);
                    XMLUtility.addStudentToUniversity(student);
                } catch (NumberFormatException e) {
                    // Handle the individual parsing error for each student
                    // Redirect to an error page or show error message
                }
            }
            response.sendRedirect("students");
        } else if ("delete".equals(action)) {
            // Delete student action
            String studentId = request.getParameter("studentId");
            XMLUtility.deleteStudentById(studentId);
            response.sendRedirect("students");
        } else if ("edit".equals(action)) {
            // Edit student action
            String studentId = request.getParameter("studentId");
            String firstName = request.getParameter("firstName");
            String gender = request.getParameter("gender");
            String level = request.getParameter("level");
            String address = request.getParameter("address");
            String gpaStr = request.getParameter("gpa");
            double gpa = 0;

            try {
                gpa = Double.parseDouble(gpaStr);
            } catch (NumberFormatException e) {
                // Handle the parsing error for GPA
                // Redirect to an error page or show error message
            }

            Student student = new Student(studentId, firstName, gender, level, address, gpa);

            // Update the student in the XML database
            XMLUtility.updateStudent(student);

            // Redirect to the students list or appropriate page
            response.sendRedirect("students");
        }
    }
}

