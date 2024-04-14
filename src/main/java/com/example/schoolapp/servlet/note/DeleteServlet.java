package com.example.schoolapp.servlet.note;

import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.metier.INoteMetier;
import com.example.schoolapp.metier.impl.ModuleMetierImpl;
import com.example.schoolapp.metier.impl.NoteMetierImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "noteDeleteServlet", value = "/notes/delete")
public class DeleteServlet extends HttpServlet {
    private INoteMetier noteMetier;

    public void init() {
        noteMetier = new NoteMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Long id = Long.valueOf(request.getParameter("id"));
        long etudiantId = Long.parseLong(request.getParameter("etudiant_id"));
        noteMetier.delete(id);
        response.sendRedirect(request.getContextPath() + "/notes?etudiant_id="+etudiantId);
    }
}