package com.example.schoolapp.servlet.admin;

import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.metier.impl.AdminMetierImpl;
import com.example.schoolapp.model.Admin;
import com.example.schoolapp.model.Filiere;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminUpdateServlet", value = "/admins/edit")
public class UpdateServlet extends HttpServlet {
    private AdminMetierImpl adminMetier;

    public void init() {
        adminMetier = new AdminMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        // Forward the request to the JSP page
        Long studentId = Long.valueOf(request.getParameter("id"));
        Admin admin = adminMetier.get(studentId);
        request.setAttribute("admin",admin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/modifier.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        Long id = Long.valueOf(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");

        // Create Admin object
        Admin admin = new Admin();
        admin.setId(id);
        admin.setNom(nom);
        admin.setPrenom(prenom);

        // Pass the Admin object to your service method
        adminMetier.update(admin, id); // Replace with your actual service method
        resp.sendRedirect(req.getContextPath() + "/admins");
    }
}
