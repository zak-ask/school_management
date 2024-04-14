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

@WebServlet(name = "filiereUpdateServlet", value = "/filieres/edit")
public class UpdateServlet extends HttpServlet {
    private IFiliereMetier filiereMetier;

    public void init() {
        filiereMetier = new FiliereMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        // Forward the request to the JSP page
        Long id = request.getParameter("id") != null ? Long.valueOf(request.getParameter("id")) :
                null;
        request.setAttribute("filiere",filiereMetier.get(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/filiere/modifier.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        Long id = Long.valueOf(req.getParameter("id"));
        String libelle = req.getParameter("libelle");
        String description = req.getParameter("description");

        // Create Etudiant object
        Filiere filiere = new Filiere();
        filiere.setLibelle(libelle);
        filiere.setDescription(description);

        // Pass the Etudiant object to your service method
        filiereMetier.update(filiere,id); // Replace with your actual service method
        resp.sendRedirect(req.getContextPath() + "/filieres");
    }
}
