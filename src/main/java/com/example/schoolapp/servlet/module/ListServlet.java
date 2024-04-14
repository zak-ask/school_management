package com.example.schoolapp.servlet.module;

import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.metier.impl.FiliereMetierImpl;
import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.metier.impl.ModuleMetierImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "moduleListServlet", value = "/modules")
public class ListServlet extends HttpServlet {
    private IModuleMetier moduleMetier;

    public void init() {
        moduleMetier = new ModuleMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        int page = request.getParameter("page") != null
                ? Integer.parseInt(request.getParameter("page")) : 1;
        int size = request.getParameter("size") != null
                ? Integer.parseInt(request.getParameter("page")) : 10;
        request.setAttribute("modules", moduleMetier.page(page, size));

        // Forward the request to the JSP page

        RequestDispatcher dispatcher = request.getRequestDispatcher("/module/list.jsp");
        dispatcher.forward(request, response);
    }
}