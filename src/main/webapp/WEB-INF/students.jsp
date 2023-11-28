<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Students</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .my-card {
            margin-bottom: 20px;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .search-card, .add-card, .students-table {
            box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
        }

        .header-spacing {
            margin-bottom: 2rem;
        }

        .button-spacing {
            margin-left: 8px;
        }
    </style>
</head>
<body class="container">


<h1 class="header-spacing">List of Students</h1>

<!-- Search and Add Forms Card -->
<div class="card search-card my-card">
    <div class="card-body">
        <!-- Search form for first name -->
        <form action="students" method="get" class="form-inline mb-3">
            <input type="hidden" name="action" value="searchByName"/>
            <div class="form-group mr-2">
                <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First name"
                       required/>
            </div>
            <button type="submit" class="btn btn-primary button-spacing">Search by Name</button>
            <a href="students?action=list" class="btn btn-outline-secondary button-spacing">Clear</a>
        </form>

        <!-- Search form for GPA -->
        <form action="students" method="get" class="form-inline">
            <input type="hidden" name="action" value="filterByGpa"/>
            <div class="form-group mr-2">
                <input type="text" id="gpa" name="gpa" class="form-control" placeholder="GPA" required/>
            </div>
            <button type="submit" class="btn btn-primary button-spacing">Filter by GPA</button>
            <a href="students?action=list" class="btn btn-outline-secondary button-spacing">Clear</a>
        </form>
    </div>
</div>

<!-- Add Students Form Card -->
<div class="card add-card my-card">
    <div class="card-body">
        <form action="students" method="get">
            <input type="hidden" name="action" value="add"/>
            <div class="form-group">
                <label for="numStudents">Number of Students to Add:</label>
                <input type="number" id="numStudents" name="numStudents" class="form-control" min="1" required/>
            </div>
            <button type="submit" class="btn btn-success">Go to Add Students</button>
        </form>
    </div>
</div>

<!-- Table of Students Card -->
<div class="card students-table my-card">
    <div class="card-body">
        <table class="table table-striped mb-0">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Gender</th>
                <th>Level</th>
                <th>Address</th>
                <th>GPA</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.firstName}</td>
                    <td>${student.gender}</td>
                    <td>${student.level}</td>
                    <td>${student.address}</td>
                    <td>${student.gpa}</td>
                    <td>
                        <!-- Form for deleting a student -->
                        <form action="students" method="post" class="form-inline">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="studentId" value="${student.id}"/>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<footer class="bg-light text-center text-lg-start">
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © FCAI Student Management System
    </div>
</footer>

</body>
</html>
