<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Students</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        fieldset {
            margin-top: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        legend {
            width: auto;
            padding: 0 5px;
            font-size: 1.1em;
            font-weight: bold;
        }
        .btn-custom {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="mb-4">Add Students</h1>
    <form action="students" method="post" class="mb-3">
        <input type="hidden" name="action" value="add"/>
        <c:forEach begin="1" end="${numStudents}" varStatus="loop">
            <fieldset>
                <legend>Student ${loop.index}</legend>
                <!-- First Name -->
                <div class="form-group">
                    <label for="student-${loop.index}-firstName">First Name:</label>
                    <input type="text" class="form-control" name="firstName" placeholder="Enter first name" required/>
                </div>
                <!-- Gender -->
                <div class="form-group">
                    <label for="student-${loop.index}-gender">Gender:</label>
                    <select class="form-control" name="gender" required>
                        <option value="">Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                </div>
                <!-- Level -->
                <div class="form-group">
                    <label for="student-${loop.index}-level">Level:</label>
                    <input type="text" class="form-control" name="level" placeholder="Enter level" required/>
                </div>
                <!-- Address -->
                <div class="form-group">
                    <label for="student-${loop.index}-address">Address:</label>
                    <input type="text" class="form-control" name="address" placeholder="Enter address" required/>
                </div>
                <!-- GPA -->
                <div class="form-group">
                    <label for="student-${loop.index}-gpa">GPA:</label>
                    <input type="text" class="form-control" name="gpa" placeholder="Enter GPA" required/>
                </div>
            </fieldset>
        </c:forEach>
        <div class="form-group">
            <input type="submit" class="btn btn-primary btn-custom" value="Add Students"/>
            <a href="students?action=list" class="btn btn-secondary">Back to List</a>
        </div>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
