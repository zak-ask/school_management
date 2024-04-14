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
        <h1 class="my-5">Modifier note</h1>
        <div class="card" >
          <ul class="list-group list-group-flush">
            <li class="list-group-item">Etudiant: ${note.etudiant.nom} ${note.etudiant.prenom}</li>
            <li class="list-group-item">Fileire: ${note.module.filiere.libelle}</li>
            <li class="list-group-item">Module: ${note.module.libelle}  | ${note.module.semestre}</li>
          </ul>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/notes/edit" class="row g-3">

          <div class="mb-3 col-md-6">
            <input type="hidden" class="form-control" id="etudiant_id" name="etudiant_id" value="${note.etudiant.id}" readonly required>
            <input type="hidden" class="form-control" id="note_id" name="note_id" value="${note.id}" readonly required>
            <label for="note" class="form-label">Note</label>
            <input type="text" class="form-control" id="note" name="note" value="${note.note}" required>
          </div>
          <div class="col-12 d-flex m-4 gap-4">
            <button type="submit" class="btn btn-primary px-4">Modifier</button>
            <a href="${pageContext.request.contextPath}/notes?etudiant_id=${note.etudiant.id}" type="submit" class="btn btn-danger px-4 ">Annuler</a>
          </div>
        </form>
      </div>
    </main>
    <jsp:include page="../footer.jsp"/>
  </div>
</div>
</body>
</html>