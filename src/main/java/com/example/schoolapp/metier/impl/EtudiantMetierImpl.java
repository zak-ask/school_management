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
        return dao.findById(id);
    }

    @Override
    public PageDTO<Etudiant> page(int page, int size) {
        if (page > 0) page -= 1;
        if (page < 0) page = 0;
        return dao.findAll(PageRequest.builder().page(page).size(size).build());
    }

    @Override
    public Etudiant create(Etudiant dto) {
        dto.setPassword(PasswordHashing.hashPassword(dto.getPassword()));
        dto.setEmail(dto.getEmail().toLowerCase());
        return dao.create(dto);
    }

    @Override
    public Etudiant update(Etudiant dto, Long id) {
        int aff = dao.update(dto, id);
        if (aff > 0){
            dto.setId(id);
            return dto;
        }else{
            throw new RuntimeException("Error");
        }
    }

    @Override
    public void delete(Long id) {

    }
}
