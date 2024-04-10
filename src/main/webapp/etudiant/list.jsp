<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Etudiants</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/ae360af17e.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<div class="wrapper">
    <jsp:include page="../sidebar.jsp"/>
    <div class="main">
        <jsp:include page="../navbar.jsp"/>
        <main class="content px-3 py-2">
            <div class="container-fluid">
                <!-- oussama this place is the start of tab -->
                <a href="${pageContext.request.contextPath}/etudiants/create" class="btn bg-primary mb-4">Ajouter Etudiant</a>
                <div class="card border-0">
                    <div class="card-header">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h5 class="card-title">
                                    Table Des Etudiants
                                </h5>
                            </div>
                            <div class="row">
                                <div class="col d-flex">
                                    <div class="col">1/10</div>
                                    <div><i class="fa fa-angle-left me-2"></i></div>
                                    <div><i class="fa fa-angle-right"></i></div>
                                </div>
                                <div class="col">total rows : ${students.content.size()}</div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nom</th>
                                <th scope="col">cin</th>
                                <th scope="col">email</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- Loop through the students and generate table rows -->
                            <c:forEach items="${students.content}" var="student">
                                <tr>
                                    <td>${student.id}</td>
                                    <td>${student.prenom} ${student.nom}</td>
                                    <td>${student.cin}</td>
                                    <td>${student.email}</td>
                                    <td>
                                        <div class="gap-2">
                                        <a class="btn bg-danger me-2" href="${pageContext.request.contextPath}/etudiants/delete?id=${student.id}" ><i class="fa-solid fa-trash"></i></a>
                                        <a class="btn bg-success" href="${pageContext.request.contextPath}/etudiants/edit?id=${student.id}"><i class="fa-solid fa-edit"></i></a></div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../footer.jsp"/>
    </div>
</div>
</body>

</html>
