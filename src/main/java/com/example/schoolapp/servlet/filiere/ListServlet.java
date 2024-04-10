package com.example.schoolapp.servlet.filiere;

import com.example.schoolapp.metier.impl.FiliereMetierImpl;
import com.example.schoolapp.metier.IFiliereMetier;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "filiereListServlet", value = "/filieres")
public class ListServlet extends HttpServlet {
    private IFiliereMetier filiereMetier;

    public void init() {
        filiereMetier = new FiliereMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        int page = request.getParameter("page") != null
                ? Integer.parseInt(request.getParameter("page")) : 1;
        int size = request.getParameter("size") != null
                ? Integer.parseInt(request.getParameter("page")) : 10;
        request.setAttribute("filieres", filiereMetier.page(page, size));

        // Forward the request to the JSP page

        RequestDispatcher dispatcher = request.getRequestDispatcher("/filiere/list.jsp");
        dispatcher.forward(request, response);
    }
}