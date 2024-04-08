<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ajouter</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css">
  <style>
    body {
      padding-top: 50px;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Modifier un Etudiant</h2>
  <hr>
  <form action="/your_servlet_url" method="post">
    <div class="mb-3">
      <label for="name" class="form-label">Nom</label>
      <input type="text" class="form-control" id="name" name="name" value="${etudiant.name}" required>
    </div>
    <div class="mb-3">
      <label for="prename" class="form-label">Prénom</label>
      <input type="text" class="form-control" id="prename" name="prename" value="${etudiant.prename}" required>
    </div>
    <div class="mb-3">
      <label class="form-check-label">Genre</label><br>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="gender" id="gender-male" value="male" <c:if test="${etudiant.gender eq 'male'}">checked</c:if> required>
        <label class="form-check-label" for="gender-male">Masculin</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="gender" id="gender-female" value="female" <c:if test="${etudiant.gender eq 'female'}">checked</c:if> required>
        <label class="form-check-label" for="gender-female">Féminin</label>
      </div>
    </div>
    <div class="mb-3">
      <label for="cin" class="form-label">CIN</label>
      <input type="text" class="form-control" id="cin" name="cin" value="${etudiant.cin}" required>
    </div>
    <div class="mb-3">
      <label for="address" class="form-label">Adresse</label>
      <input type="text" class="form-control" id="address" name="address" value="${etudiant.address}" required>
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="email" class="form-control" id="email" name="email" value="${etudiant.email}" required>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">Mot de passe</label>
      <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <button type="submit" class="btn btn-primary">Modifier Etudiant</button>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.4.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
