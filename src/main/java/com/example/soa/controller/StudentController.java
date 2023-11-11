package com.example.soa.controller;

import com.example.soa.model.Student;
import com.example.soa.model.University;
import com.example.soa.utility.XmlUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.List;

@WebServlet("/studentController")
public class StudentController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Action parameter to determine which action to perform
        String action = request.getParameter("action");

        try {
            // List all students
            if ("list".equals(action)) {
                University university = XmlUtility.unmarshalFromFile();
                request.setAttribute("students", university.getStudents());
                request.getRequestDispatcher("/list-students.jsp").forward(request, response);
            }
            // Search for students by GPA
            else if ("searchByGpa".equals(action)) {
                Double gpa = Double.valueOf(request.getParameter("gpa"));
                List<Student> students = XmlUtility.findStudents(null, gpa);
                request.setAttribute("students", students);
                request.getRequestDispatcher("/search-results.jsp").forward(request, response);
            }
            // Search for students by Name
            else if ("searchByName".equals(action)) {
                String name = request.getParameter("name");
                List<Student> students = XmlUtility.findStudents(name, null);
                request.setAttribute("students", students);
                request.getRequestDispatcher("/search-results.jsp").forward(request, response);
            }
        } catch (JAXBException e) {
            throw new ServletException("JAXBException in doGet", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Action parameter to determine which action to perform
        String action = request.getParameter("action");

        try {
            // Add new student
            if ("add".equals(action)) {
                Student student = new Student();
                // Assume there's a method to populate a student object from request parameters
                student.setId(request.getParameter("id"));
                student.setFirstName(request.getParameter("firstName"));
                student.setLastName(request.getParameter("lastName"));
                student.setGender(request.getParameter("gender"));
                student.setGpa(Double.parseDouble(request.getParameter("gpa")));
                student.setLevel(Integer.parseInt(request.getParameter("level")));
                student.setAddress(request.getParameter("address"));
                XmlUtility.addStudent(student);
                response.sendRedirect("studentController?action=list");
            }
            // Delete a student
            else if ("delete".equals(action)) {
                String studentId = request.getParameter("studentId");
                XmlUtility.deleteStudent(studentId);
                response.sendRedirect("studentController?action=list");
            }
        } catch (JAXBException e) {
            throw new ServletException("JAXBException in doPost", e);
        }
    }
}
