package com.example.soa.controller;


import com.example.soa.utility.XMLUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CheckStudentId")
public class CheckStudentIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("id");
        boolean exists = XMLUtility.doesStudentIdExist(studentId);
        response.setContentType("text/plain");
        response.getWriter().write(exists ? "exists" : "not exists");
    }
}