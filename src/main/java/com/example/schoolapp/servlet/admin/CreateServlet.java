package com.example.schoolapp.servlet.admin;

import com.example.schoolapp.metier.IAdminMetier;
import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.metier.impl.AdminMetierImpl;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;
import com.example.schoolapp.metier.impl.FiliereMetierImpl;
import com.example.schoolapp.model.Admin;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.model.Filiere;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adCreateServlet", value = "/admins/create")
public class CreateServlet extends HttpServlet {
    private IAdminMetier adminMetier;

    public void init() {
        adminMetier = new AdminMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/ajouter.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // Retrieve form data
        String nom = (req.getParameter("nom") != null) ? req.getParameter("nom") : "";
        String prenom = (req.getParameter("prenom") != null) ? req.getParameter("prenom") : "";
        String password = (req.getParameter("password") != null) ? req.getParameter("password") : "";
        String email = (req.getParameter("email") != null) ? req.getParameter("email") : "";

            Admin admin = new Admin();
            admin.setNom(nom);
            admin.setPrenom(prenom);
            admin.setPassword(password);
            admin.setEmail(email);
            adminMetier.create(admin); // Replace with your actual service method
         resp.sendRedirect(req.getContextPath() + "/admins");
    }
}
