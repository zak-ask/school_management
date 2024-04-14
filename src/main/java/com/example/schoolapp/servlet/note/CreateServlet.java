package com.example.schoolapp.servlet.note;

import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.metier.impl.FiliereMetierImpl;
import com.example.schoolapp.metier.impl.ModuleMetierImpl;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.model.Module;
import com.example.schoolapp.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "noteCreateServlet", value = "/notes/create")
public class CreateServlet extends HttpServlet {
    private IFiliereMetier filiereMetier;
    private IModuleMetier moduleMetier;

    public void init() {
        filiereMetier = new FiliereMetierImpl();
        moduleMetier = new ModuleMetierImpl();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException {
        // Forward the request to the JSP page
        String libelle = req.getParameter("libelle");
        String description = req.getParameter("description");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/module/ajouter.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        String libelle = req.getParameter("libelle");
        String description = req.getParameter("description");
        String semestre  = req.getParameter("semestre");
        Long filiereId = Long.valueOf(req.getParameter("filiereId"));

        Module module = new Module();
        module.setLibelle(libelle);
        module.setDescription(description);
        moduleMetier.create(Module.builder().semestre(semestre)
                        .libelle(libelle).description(description)
                .filiere(Filiere.builder().id(filiereId).build())
                .build());
        // Redirect the user to /modules
        resp.sendRedirect(req.getContextPath() + "/modules");
    }
}
