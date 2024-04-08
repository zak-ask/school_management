package com.example.schoolapp.servlet.filiere;

import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "filiereListServlet", value = "/filieres")
public class ListServlet extends HttpServlet {
    private IEtudiantMetier etudiantMetier;

    public void init() {
        etudiantMetier = new EtudiantMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        request.setAttribute("students", etudiantMetier.page(1, 10));

        // Forward the request to the JSP page

        RequestDispatcher dispatcher = request.getRequestDispatcher("/filiere/list.jsp");
        dispatcher.forward(request, response);
    }
}