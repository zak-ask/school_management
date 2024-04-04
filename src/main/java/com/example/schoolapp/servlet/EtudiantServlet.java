package com.example.schoolapp.servlet;

import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "etudiantServlet", value = "/etudiants")
public class EtudiantServlet extends HttpServlet {
    private IEtudiantMetier etudiantMetier;

    public void init() {
        etudiantMetier = new EtudiantMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        request.setAttribute("students", etudiantMetier.page(1,10));

        // Forward the request to the JSP page

        RequestDispatcher dispatcher = request.getRequestDispatcher("/students.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy() {
    }
}