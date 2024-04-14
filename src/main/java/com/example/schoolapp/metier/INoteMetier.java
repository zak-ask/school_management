package com.example.schoolapp.metier;

import com.example.schoolapp.model.Note;

import java.util.List;

public interface INoteMetier extends ICrudMetier<Note>{
    List<Note> findAllByStudentId(Long etudiantId);
    Note create(Long etudiantId, Long moduleId, Double note);
}
