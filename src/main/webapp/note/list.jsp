<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des notes</title>
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
                <div class="card border-0">
                    <div class="card-header">
                        <h5 class="card-title">
                            Table Des Notes pour l'étudiant ${notes.get(0).etudiant.nom} ${notes.get(0).etudiant.prenom}
                        </h5>
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">filière</th>
                                <th scope="col">module</th>
                                <th scope="col">note</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- Loop through the students and generate table rows -->
                            <c:forEach items="${notes}" var="note">
                                <tr>
                                    <td>${note.module.filiere.libelle}</td>
                                    <td>${note.module.libelle}</td>
                                    <c:choose>
                                        <c:when test="${note.id eq 0}">
                                        <td>N/A</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${note.note}</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>
                                        <div class="gap-2">
                                            <c:choose>
                                                <c:when test="${note.id eq 0}">
                                                    <a class="btn bg-success" href="${pageContext.request.contextPath}/notes/create?module_id=${note.module.id}&etudiant_id=${note.etudiant.id}"><i class="fa-solid fa-edit"></i></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="btn bg-danger me-2" href="${pageContext.request.contextPath}/notes/delete?id=${note.id}&etudiant_id=${notes.get(0).etudiant.id}" ><i class="fa-solid fa-trash"></i></a>
                                                    <a class="btn bg-success" href="${pageContext.request.contextPath}/notes/edit?id=${note.id}"><i class="fa-solid fa-edit"></i></a>
                                                </c:otherwise>
                                            </c:choose>
                                       </div>
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
