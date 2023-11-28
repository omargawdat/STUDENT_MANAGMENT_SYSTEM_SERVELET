<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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


<div class="card search-card my-card">
    <div class="card-body">
        <!-- Tab Navigation -->
        <ul class="nav nav-tabs" id="searchTabs" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="gpa-tab" data-toggle="tab" href="#gpaSearch" role="tab"
                   aria-controls="gpaSearch" aria-selected="true">GPA</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="name-tab" data-toggle="tab" href="#nameSearch" role="tab"
                   aria-controls="nameSearch" aria-selected="false">Name</a>
            </li>

            <!-- Tab for ID -->
            <li class="nav-item">
                <a class="nav-link" id="id-tab" data-toggle="tab" href="#idSearch" role="tab" aria-controls="idSearch"
                   aria-selected="false">ID</a>
            </li>

            <!-- Tab for Gender -->
            <li class="nav-item">
                <a class="nav-link" id="gender-tab" data-toggle="tab" href="#genderSearch" role="tab"
                   aria-controls="genderSearch" aria-selected="false">Gender</a>
            </li>
            <!-- Tab for Level -->
            <li class="nav-item">
                <a class="nav-link" id="level-tab" data-toggle="tab" href="#levelSearch" role="tab"
                   aria-controls="levelSearch" aria-selected="false">Level</a>
            </li>

            <!-- Tab for Address -->
            <li class="nav-item">
                <a class="nav-link" id="address-tab" data-toggle="tab" href="#addressSearch" role="tab"
                   aria-controls="addressSearch" aria-selected="false">Address</a>
            </li>
        </ul>

        <!-- Tab Content -->
        <div class="tab-content" id="searchTabsContent">
            <!-- GPA Search Tab -->
            <div class="tab-pane fade show active" id="gpaSearch" role="tabpanel" aria-labelledby="gpa-tab">
                <!-- Search form for GPA -->
                <form action="students" method="get" class="form-inline mt-3">
                    <input type="hidden" name="action" value="filterByGpa"/>
                    <input type="text" id="gpa" name="gpa" class="form-control mr-2" placeholder="GPA" required/>
                    <button type="submit" class="btn btn-primary mr-2">Search</button>
                    <a href="students?action=list" class="btn btn-outline-secondary">Clear</a>
                </form>
            </div>
            <!-- Name Search Tab -->
            <div class="tab-pane fade" id="nameSearch" role="tabpanel" aria-labelledby="name-tab">
                <!-- Search form for first name -->
                <form action="students" method="get" class="form-inline mt-3">
                    <input type="hidden" name="action" value="searchByName"/>
                    <input type="text" id="firstName" name="firstName" class="form-control mr-2"
                           placeholder="First name" required/>
                    <button type="submit" class="btn btn-primary mr-2">Search</button>
                    <a href="students?action=list" class="btn btn-outline-secondary">Clear</a>
                </form>
            </div>


            <!-- ID Search Tab -->
            <div class="tab-pane fade" id="idSearch" role="tabpanel" aria-labelledby="id-tab">
                <form action="students" method="get" class="form-inline mt-3">
                    <input type="hidden" name="action" value="searchById"/>
                    <input type="text" id="id" name="id" class="form-control mr-2" placeholder="Student ID" required/>
                    <button type="submit" class="btn btn-primary mr-2">Search</button>
                    <a href="students?action=list" class="btn btn-outline-secondary">Clear</a>
                </form>
            </div>

            <!-- Gender Search Tab -->
            <div class="tab-pane fade" id="genderSearch" role="tabpanel" aria-labelledby="gender-tab">
                <form action="students" method="get" class="form-inline mt-3">
                    <input type="hidden" name="action" value="searchByGender"/>
                    <select id="gender" name="gender" class="form-control mr-2" required>
                        <option value="">Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                    <button type="submit" class="btn btn-primary mr-2">Search</button>
                    <a href="students?action=list" class="btn btn-outline-secondary">Clear</a>
                </form>
            </div>

            <!-- Level Search Tab -->
            <div class="tab-pane fade" id="levelSearch" role="tabpanel" aria-labelledby="level-tab">
                <form action="students" method="get" class="form-inline mt-3">
                    <input type="hidden" name="action" value="searchByLevel"/>
                    <input type="text" id="level" name="level" class="form-control mr-2" placeholder="Level" required/>
                    <button type="submit" class="btn btn-primary mr-2">Search</button>
                    <a href="students?action=list" class="btn btn-outline-secondary">Clear</a>
                </form>
            </div>

            <!-- Address Search Tab -->
            <div class="tab-pane fade" id="addressSearch" role="tabpanel" aria-labelledby="address-tab">
                <form action="students" method="get" class="form-inline mt-3">
                    <input type="hidden" name="action" value="searchByAddress"/>
                    <input type="text" id="address" name="address" class="form-control mr-2" placeholder="Address"
                           required/>
                    <button type="submit" class="btn btn-primary mr-2">Search</button>
                    <a href="students?action=list" class="btn btn-outline-secondary">Clear</a>
                </form>
            </div>


        </div>
    </div>
</div>

<div class="my-card">
    <h5>Number of Students Found: <c:out value="${fn:length(students)}"/></h5>
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
                <th>
                    ID
                    <a href="students?action=sort&order=asc&field=id">↑</a>
                    <a href="students?action=sort&order=desc&field=id">↓</a>
                </th>
                <th>
                    First Name
                    <a href="students?action=sort&order=asc&field=firstName">↑</a>
                    <a href="students?action=sort&order=desc&field=firstName">↓</a>
                </th>
                <th>
                    Gender
                    <a href="students?action=sort&order=asc&field=gender">↑</a>
                    <a href="students?action=sort&order=desc&field=gender">↓</a>
                </th>
                <th>
                    Level
                    <a href="students?action=sort&order=asc&field=level">↑</a>
                    <a href="students?action=sort&order=desc&field=level">↓</a>
                </th>
                <th>
                    Address
                    <a href="students?action=sort&order=asc&field=address">↑</a>
                    <a href="students?action=sort&order=desc&field=address">↓</a>
                </th>
                <th>
                    GPA
                    <a href="students?action=sort&order=asc&field=gpa">↑</a>
                    <a href="students?action=sort&order=desc&field=gpa">↓</a>
                </th>
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
                        <!-- Form for editing a student -->
                        <form action="students" method="get" class="form-inline" style="display: inline;">
                            <input type="hidden" name="action" value="edit"/>
                            <input type="hidden" name="studentId" value="${student.id}"/>
                            <button type="submit" class="btn btn-primary button-spacing">Edit</button>
                        </form>
                        <!-- Existing Delete Form -->
                        <form action="students" method="post" class="form-inline" style="display: inline;">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="studentId" value="${student.id}"/>
                            <button type="submit" class="btn btn-danger button-spacing">Delete</button>
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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
