package com.example.schoolapp.servlet.etudiant;

import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "etListServlet", value = "/etudiants")
public class ListServlet extends HttpServlet {
    private IEtudiantMetier etudiantMetier;

    public void init() {
        etudiantMetier = new EtudiantMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        int page = request.getParameter("page") != null
                ? Integer.parseInt(request.getParameter("page")) : 1;
        int size = request.getParameter("size") != null
                ? Integer.parseInt(request.getParameter("page")) : 10;
        request.setAttribute("students", etudiantMetier.page(page, size));

        // Forward the request to the JSP page

        RequestDispatcher dispatcher = request.getRequestDispatcher("/etudiant/list.jsp");
        dispatcher.forward(request, response);
    }
}