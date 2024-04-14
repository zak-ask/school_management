package com.example.schoolapp.servlet.note;

import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.metier.INoteMetier;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;
import com.example.schoolapp.metier.impl.FiliereMetierImpl;
import com.example.schoolapp.metier.impl.ModuleMetierImpl;
import com.example.schoolapp.metier.impl.NoteMetierImpl;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.model.Module;
import com.example.schoolapp.model.Note;
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
    private IEtudiantMetier etudiantMetier;
    private IModuleMetier moduleMetier;
    private INoteMetier noteMetier;

    public void init() {
        etudiantMetier = new EtudiantMetierImpl();
        moduleMetier = new ModuleMetierImpl();
        noteMetier = new NoteMetierImpl();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException {
        // Forward the request to the JSP page
        Long etudiantId = Long.valueOf(req.getParameter("etudiant_id"));
        Long moduleId = Long.valueOf(req.getParameter("module_id"));
        Etudiant etudiant = etudiantMetier.get(etudiantId);
        Module module = moduleMetier.get(moduleId);
        req.setAttribute("etudiant", etudiant);
        req.setAttribute("module", module);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/note/ajouter.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        Double note = req.getParameter("note") != null ? Double.parseDouble(req.getParameter("note")) : 0.;
        Long etudiantId = Long.valueOf(req.getParameter("etudiant_id"));
        Long moduleId = Long.valueOf(req.getParameter("module_id"));
        noteMetier.create(etudiantId, moduleId, note);
        resp.sendRedirect(req.getContextPath() + "/notes?etudiant_id="+etudiantId);
    }
}
