package com.example.schoolapp.servlet.etudiant;

import com.example.schoolapp.metier.impl.FiliereMetierImpl;
import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.IFiliereMetier;
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

@WebServlet(name = "etUpdateServlet", value = "/etudiants/edit")
public class UpdateServlet extends HttpServlet {
    private IEtudiantMetier etudiantMetier;
    private IFiliereMetier filiereMetier;

    public void init() {
        etudiantMetier = new EtudiantMetierImpl();
        filiereMetier = new FiliereMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        // Forward the request to the JSP page
        Long studentId = Long.valueOf(request.getParameter("id"));
        Etudiant etudiant = etudiantMetier.get(studentId);
        Filiere selectedFiliere = filiereMetier.getByStudentId(studentId);
        request.setAttribute("etudiant",etudiant);
//        request.setAttribute("selectedFiliere",selectedFiliere);
        request.setAttribute("filieres", filiereMetier.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/etudiant/modifier.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        Long id = Long.valueOf(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String cin = req.getParameter("cin");
        Long filiereId = Long.valueOf(req.getParameter("filiereId"));

        // Create Etudiant object
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setCin(cin);
        etudiant.setFiliere(Filiere.builder().id(filiereId).build());

        // Pass the Etudiant object to your service method
        etudiantMetier.update(etudiant, id); // Replace with your actual service method
        resp.sendRedirect(req.getContextPath() + "/etudiants");
    }
}
