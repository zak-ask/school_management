package com.example.schoolapp.servlet.module;

import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.metier.impl.FiliereMetierImpl;
import com.example.schoolapp.metier.impl.ModuleMetierImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "moduleDeleteServlet", value = "/modules/delete")
public class DeleteServlet extends HttpServlet {
    private IModuleMetier moduleMetier;

    public void init() {
        moduleMetier = new ModuleMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Long id = Long.valueOf(request.getParameter("id"));
        moduleMetier.delete(id);
        response.sendRedirect(request.getContextPath() + "/modules");
    }
}