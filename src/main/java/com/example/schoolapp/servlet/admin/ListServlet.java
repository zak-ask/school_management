package com.example.schoolapp.servlet.admin;

import com.example.schoolapp.metier.IAdminMetier;
import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.metier.impl.AdminMetierImpl;
import com.example.schoolapp.metier.impl.EtudiantMetierImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adListServlet", value = "/admins")
public class ListServlet extends HttpServlet {
    private IAdminMetier adminMetier;

    public void init() {
        adminMetier = new AdminMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        int page = request.getParameter("page") != null
                ? Integer.parseInt(request.getParameter("page")) : 1;
        int size = request.getParameter("size") != null
                ? Integer.parseInt(request.getParameter("page")) : 10;
        request.setAttribute("admins", adminMetier.page(page, size));

        // Forward the request to the JSP page

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/list.jsp");
        dispatcher.forward(request, response);
    }
}