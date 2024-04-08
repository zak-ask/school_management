package com.example.schoolapp.servlet.etudiant;

import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.model.Filiere;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "etCreateServlet", value = "/etudiants/create")
public class CreateServlet extends HttpServlet {
    private IEtudiantMetier etudiantMetier;

    public void init() {
        etudiantMetier = new EtudiantMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/etudiant/ajouter.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // Retrieve form data
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String cin = req.getParameter("cin");
            int age = Integer.parseInt(req.getParameter("age"));
            Long filiereId = Long.valueOf(req.getParameter("filiereId"));

            // Create Etudiant object
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(nom);
            etudiant.setPrenom(prenom);
            etudiant.setPassword(password);
            etudiant.setEmail(email);
            etudiant.setCin(cin);
            etudiant.setAge(age);
            etudiant.setFiliere(Filiere.builder().id(filiereId).build());

            // Pass the Etudiant object to your service method
            etudiantMetier.create(etudiant); // Replace with your actual service method
    }
}
