package com.example.schoolapp.servlet.etudiant;

import com.example.schoolapp.metier.IAdminMetier;
import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.impl.AdminMetierImpl;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "etudiantDeleteServlet", value = "/etudiants/delete")
public class DeleteServlet extends HttpServlet {
    private IEtudiantMetier etudiantMetier;

    public void init() {
        etudiantMetier = new EtudiantMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Long id = Long.valueOf(request.getParameter("id"));
        etudiantMetier.delete(id);
        response.sendRedirect(request.getContextPath() + "/etudiants");
    }
}