package com.example.schoolapp.servlet.admin;

import com.example.schoolapp.metier.IAdminMetier;
import com.example.schoolapp.metier.impl.AdminMetierImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminDeleteServlet", value = "/admins/delete")
public class DeleteServlet extends HttpServlet {
    private IAdminMetier adminMetier;

    public void init() {
        adminMetier = new AdminMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Long id = Long.valueOf(request.getParameter("id"));
        adminMetier.delete(id);
        response.sendRedirect(request.getContextPath() + "/admins");
    }
}