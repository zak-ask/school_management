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
        <h1 class="my-5">Modifier module</h1>
        <form method="post" action="${pageContext.request.contextPath}/modules/edit" class="row g-3">

          <div class="mb-3 col-md-6">
            <input type="hidden" class="form-control" id="id" name="id" value="${module.id}" required>
            <label for="libelle" class="form-label">Libelle</label>
            <input type="text" class="form-control" id="libelle" name="libelle" value="${module.libelle}" required>
          </div>
          <div class="mb-3 col-md-6">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control" id="description" name="description" value="${module.description}" required>
          </div>
          <div class="mb-3 col-md-6">
            <label for="filiere" class="form-label">Filiere</label>
            <select name="filiereId" id="filiere" class="form-select">
              <option value="${selectedFiliere.id}" selected>${selectedFiliere.libelle}</option>
              <c:forEach items="${filieres}" var="filiere">
                <c:if test="${filiere.id != selectedFiliere.id}">
                  <option value="${filiere.id}">${filiere.libelle}</option>
                </c:if>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3 col-md-6">
            <label for="semestre" class="form-label">Semestre</label>
            <select name="semestreId" id="semestre" class="form-select">
              <c:forEach var="semester" items="${semesters}">
                <c:choose>
                  <c:when test="${semester eq selectedSemester}">
                    <option value="${semester}" selected>${semester}</option>
                  </c:when>
                  <c:otherwise>
                    <option value="${semester}">${semester}</option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </select>
          </div>

          <div class="col-12 d-flex m-4 gap-4">
            <button type="submit" class="btn btn-primary px-4">Modifier</button>
            <a href="${pageContext.request.contextPath}/modules" type="submit" class="btn btn-danger px-4 ">Annuler</a>
          </div>
        </form>
      </div>
    </main>
    <jsp:include page="../footer.jsp"/>
  </div>
</div>
</body>
</html>