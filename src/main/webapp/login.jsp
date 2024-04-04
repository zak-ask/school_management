<%--
  Created by IntelliJ IDEA.
  User: zakaria
  Date: 3/3/2024
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<div class="container">
    <h2>Student Details</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Grade</th>
        </tr>
        </thead>
        <tbody>
        <!-- Loop through the students and generate table rows -->
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.age}</td>
                <td>${student.grade}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
