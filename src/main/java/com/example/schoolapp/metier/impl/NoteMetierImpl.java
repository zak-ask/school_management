package com.example.schoolapp.metier.impl;

import com.example.schoolapp.dao.EtudiantDao;
import com.example.schoolapp.dao.NoteDao;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.metier.INoteMetier;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.model.Module;
import com.example.schoolapp.model.Note;

import java.util.List;

public class NoteMetierImpl implements INoteMetier {
    NoteDao noteDao = new NoteDao();
    EtudiantDao etudiantDao = new EtudiantDao();
    IModuleMetier moduleMetier = new ModuleMetierImpl();
    @Override
    public Note get(Long id) {
        return noteDao.findById(id);
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
         noteDao.update(dto,id);
         return dto;
    }

    @Override
    public void delete(Long id) {
        noteDao.delete(id);
    }

    @Override
    public List<Note> findAllByStudentId(Long etudiantId) {

        return noteDao.findAllByStudent(etudiantDao.findById(etudiantId));
    }

    @Override
    public Note create(Long etudiantId, Long moduleId, Double note) {
        Module module = moduleMetier.get(moduleId);
        Etudiant etudiant = etudiantDao.findById(etudiantId);
        Note noteModel = Note.builder().module(module).etudiant(etudiant).note(note).build();
        Long createdNoteId = noteDao.create(noteModel);
        noteModel.setId(createdNoteId);
        return noteModel;
    }
}
