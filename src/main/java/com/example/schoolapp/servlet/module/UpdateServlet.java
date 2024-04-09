package com.example.schoolapp.servlet.module;

import com.example.schoolapp.metier.FiliereMetierImpl;
import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.metier.impl.ModuleMetierImpl;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.model.Module;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "moduleUpdateServlet", value = "/modules/edit")
public class UpdateServlet extends HttpServlet {
    private IModuleMetier moduleMetier;

    public void init() {
        moduleMetier = new ModuleMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        // Forward the request to the JSP page
        Long id = request.getParameter("id") != null ? Long.valueOf(request.getParameter("id")) :
                null;
        request.setAttribute("module",moduleMetier.get(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/module/modifier.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        Long id = Long.valueOf(req.getParameter("id"));
        String libelle = req.getParameter("libelle");
        String description = req.getParameter("description");
        String semestre = req.getParameter("semestre");
        Long filiereId = Long.valueOf(req.getParameter("filiere_id"));

        // Create Etudiant object
        Module module = new Module();
        module.setLibelle(libelle);
        module.setDescription(description);
        module.setSemestre(semestre);
        moduleMetier.update(module,id); // Replace with your actual service method
        resp.sendRedirect(req.getContextPath() + "/modules");
    }
}
