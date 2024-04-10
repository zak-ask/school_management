package com.example.schoolapp.metier.impl;

import com.example.schoolapp.dao.FiliereDao;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.metier.IFiliereMetier;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.utils.PageRequest;

import java.util.List;

public class FiliereMetierImpl implements IFiliereMetier {
    private final FiliereDao dao = new FiliereDao();
    @Override
    public Filiere get(Long id) {
        if (id == null){
            throw new RuntimeException("Error: bad request.");
        }
        return dao.findById(id);
    }

    @Override
    public PageDTO<Filiere> page(int page, int size) {
        if (page > 0) page -= 1;
        if (page < 0) page = 0;
        return dao.page(PageRequest.builder().page(page).size(size).build());
    }

    @Override
    public Filiere create(Filiere dto) {
        int aff = dao.create(dto);
        return dto;
    }

    @Override
    public Filiere update(Filiere dto, Long id) {
        dao.update(dto);
        return dto;
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Filiere> getAll() {
        return dao.findAll();
    }

    @Override
    public Filiere getByStudentId(Long studentId) {
        return dao.findByStudentId(studentId);
    }
}
