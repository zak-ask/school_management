package com.example.schoolapp.metier.impl;

import com.example.schoolapp.dao.EtudiantDao;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.metier.IEtudiantMetier;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.utils.PageRequest;
import com.example.schoolapp.utils.PasswordHashing;


public class EtudiantMetierImpl implements IEtudiantMetier {
    private final EtudiantDao dao;

    public EtudiantMetierImpl() {
        this.dao = new EtudiantDao();
    }

    @Override
    public Etudiant get(Long id) {
        return null;
    }

    @Override
    public PageDTO<Etudiant> page(int page, int size) {
        return dao.findAll(PageRequest.builder().page(page-1).size(size).build());
    }

    @Override
    public Etudiant create(Etudiant dto) {
        dto.setPassword(PasswordHashing.hashPassword(dto.getPassword()));
        return dao.create(dto);
    }

    @Override
    public Etudiant update(Etudiant dto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
