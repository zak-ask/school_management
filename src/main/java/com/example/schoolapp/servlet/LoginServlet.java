package com.example.schoolapp.servlet;

import com.example.schoolapp.metier.ICompteMetier;
import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.impl.CompteMetierImpl;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    ICompteMetier compteMetier;

    public void init() {
        compteMetier = new CompteMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        // Forward the request to the JSP page

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public void destroy() {
    }
}