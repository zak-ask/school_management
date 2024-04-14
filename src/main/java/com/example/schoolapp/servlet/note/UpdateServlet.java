package com.example.schoolapp.servlet.note;

import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.metier.INoteMetier;
import com.example.schoolapp.metier.impl.FiliereMetierImpl;
import com.example.schoolapp.metier.impl.ModuleMetierImpl;
import com.example.schoolapp.metier.impl.NoteMetierImpl;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.model.Module;
import com.example.schoolapp.model.Note;
import com.example.schoolapp.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "noteUpdateServlet", value = "/notes/edit")
public class UpdateServlet extends HttpServlet {
    private INoteMetier noteMetier;

    public void init() {
        noteMetier = new NoteMetierImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        long noteId = Long.parseLong(request.getParameter("id"));
        Note note = noteMetier.get(noteId);
        request.setAttribute("note",note);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/note/modifier.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        double note = Double.parseDouble(req.getParameter("note"));
        Long noteId = Long.parseLong(req.getParameter("note_id"));
        Long etudiantId = Long.parseLong(req.getParameter("etudiant_id"));
        Note noteModel = Note.builder().id(noteId).note(note).build();
        noteMetier.update(noteModel,noteId);
        resp.sendRedirect(req.getContextPath() + "/notes?etudiant_id="+ etudiantId);
    }
}
