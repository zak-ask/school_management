package com.example.schoolapp.servlet.filiere;

import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;
import com.example.schoolapp.metier.impl.FiliereMetierImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "filiereDeleteServlet", value = "/filieres/delete")
public class DeleteServlet extends HttpServlet {
    private IFiliereMetier filiereMetier;

    public void init() {
        filiereMetier = new FiliereMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Long id = Long.valueOf(request.getParameter("id"));
        filiereMetier.delete(id);
        response.sendRedirect(request.getContextPath() + "/filieres");
    }
}