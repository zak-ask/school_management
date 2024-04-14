package com.example.schoolapp.metier.impl;

import com.example.schoolapp.dao.EtudiantDao;
import com.example.schoolapp.dao.NoteDao;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.metier.INoteMetier;
import com.example.schoolapp.model.Note;

import java.util.List;

public class NoteMetierImpl implements INoteMetier {
    NoteDao noteDao = new NoteDao();
    EtudiantDao etudiantDao = new EtudiantDao();
    @Override
    public Note get(Long id) {
        return null;
    }

    @Override
    public PageDTO<Note> page(int page, int size) {
        return null;
    }

    @Override
    public Note create(Note dto) {
        return null;
    }

    @Override
    public Note update(Note dto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        noteDao.delete(id);
    }

    @Override
    public List<Note> findAllByStudentId(Long etudiantId) {

        return noteDao.findAllByStudent(etudiantDao.findById(etudiantId));
    }
}
