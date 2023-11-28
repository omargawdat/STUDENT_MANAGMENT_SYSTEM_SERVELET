<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Student</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Add any additional JavaScript here -->
</head>
<body>
<div class="container">
    <h1 class="mb-4">Edit Student</h1>
    <form action="students" method="post" class="mb-3">
        <input type="hidden" name="action" value="edit"/>
        <input type="hidden" name="studentId" value="${studentToEdit.id}"/>

        <!-- ID (Read-Only) -->
        <div class="form-group">
            <label for="student-ID">ID:</label>
            <input type="text" id="student-ID" class="form-control" name="id" value="${studentToEdit.id}" readonly/>
        </div>

        <!-- First Name -->
        <div class="form-group">
            <label for="student-firstName">First Name:</label>
            <input type="text" id="student-firstName" class="form-control" name="firstName"
                   value="${studentToEdit.firstName}"
                   pattern="[A-Za-z]+" title="First name should only contain letters." required/>
        </div>

        <!-- Gender -->
        <div class="form-group">
            <label for="student-gender">Gender:</label>
            <select id="student-gender" class="form-control" name="gender" required>
                <option value="">Select Gender</option>
                <option value="Male" ${studentToEdit.gender == 'Male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${studentToEdit.gender == 'Female' ? 'selected' : ''}>Female</option>
            </select>
        </div>

        <!-- Level -->
        <div class="form-group">
            <label for="student-level">Level:</label>
            <input type="text" id="student-level" class="form-control" name="level" value="${studentToEdit.level}"
                   required/>
        </div>

        <!-- Address -->
        <div class="form-group">
            <label for="student-address">Address:</label>
            <input type="text" id="student-address" class="form-control" name="address" value="${studentToEdit.address}"
                   pattern="[A-Za-z\s]+" title="Address should only contain letters and spaces." required/>
        </div>

        <!-- GPA -->
        <div class="form-group">
            <label for="student-gpa">GPA:</label>
            <input type="number" id="student-gpa" class="form-control" name="gpa" value="${studentToEdit.gpa}"
                   min="0" max="4" step="0.01" title="GPA must be a number between 0 and 4" required/>
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Save Changes"/>
            <a href="students?action=list" class="btn btn-secondary">Back to List</a>
        </div>
    </form>
</div>

</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
