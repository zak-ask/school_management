package com.example.schoolapp.servlet.filiere;

import com.example.schoolapp.metier.impl.FiliereMetierImpl;
import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.model.Filiere;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "filiereCreateServlet", value = "/filieres/create")
public class CreateServlet extends HttpServlet {
    private IFiliereMetier filiereMetier;

    public void init() {
        filiereMetier = new FiliereMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/filiere/ajouter.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        String libelle = req.getParameter("libelle");
        String description = req.getParameter("description");

        // Create Etudiant object
        Filiere filiere = new Filiere();
        filiere.setLibelle(libelle);
        filiere.setDescription(description);

        // Pass the Etudiant object to your service method
        filiereMetier.create(filiere); // Replace with your actual service method
        // Redirect the user to /filieres
        resp.sendRedirect(req.getContextPath() + "/filieres");
    }
}
