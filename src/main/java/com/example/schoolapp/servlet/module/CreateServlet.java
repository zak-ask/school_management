package com.example.schoolapp.servlet.module;

import com.example.schoolapp.metier.FiliereMetierImpl;
import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.model.Filiere;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "moduleCreateServlet", value = "/modules/create")
public class CreateServlet extends HttpServlet {
    private IFiliereMetier moduleMetier;

    public void init() {
        moduleMetier = new FiliereMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/module/ajouter.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        String libelle = req.getParameter("libelle");
        String description = req.getParameter("description");

        // Create Etudiant object
        Filiere module = new Filiere();
        module.setLibelle(libelle);
        module.setDescription(description);

        // Pass the Etudiant object to your service method
        moduleMetier.create(module); // Replace with your actual service method
        // Redirect the user to /modules
        resp.sendRedirect(req.getContextPath() + "/modules");
    }
}
