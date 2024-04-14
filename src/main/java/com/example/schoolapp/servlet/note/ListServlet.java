package com.example.schoolapp.servlet.note;

import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.metier.INoteMetier;
import com.example.schoolapp.metier.impl.ModuleMetierImpl;
import com.example.schoolapp.metier.impl.NoteMetierImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "noteListServlet", value = "/notes")
public class ListServlet extends HttpServlet {
    private INoteMetier noteMetier;

    public void init() {
        noteMetier = new NoteMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Long etudiantId = Long.valueOf(request.getParameter("etudiant_id"));
        request.setAttribute("notes", noteMetier.findAllByStudentId(etudiantId));

        // Forward the request to the JSP page

        RequestDispatcher dispatcher = request.getRequestDispatcher("/note/list.jsp");
        dispatcher.forward(request, response);
    }
}