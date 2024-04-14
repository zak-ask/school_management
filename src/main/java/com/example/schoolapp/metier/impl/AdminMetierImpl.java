package com.example.schoolapp.metier.impl;

import com.example.schoolapp.dao.AdminDao;
import com.example.schoolapp.dao.UtilisateurDao;
import com.example.schoolapp.model.Admin;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.metier.IAdminMetier;
import com.example.schoolapp.model.Utilisateur;
import com.example.schoolapp.utils.PageRequest;
import com.example.schoolapp.utils.PasswordHashing;

public class AdminMetierImpl implements IAdminMetier {
    private final AdminDao dao;
    private final UtilisateurDao utilisateurDao;
    public AdminMetierImpl(){
        dao = new AdminDao();
        utilisateurDao = new UtilisateurDao();
    }

    @Override
    public Admin get(Long id) {
        return dao.findById(id);
    }

    @Override
    public PageDTO<Admin> page(int page, int size) {
        if (page > 0) page -= 1;
        if (page < 0) page = 0;
        return dao.findAll(PageRequest.builder().page(page).size(size).build());
    }

    @Override
    public Admin create(Admin dto) {
        dto.setPassword(PasswordHashing.hashPassword(dto.getPassword()));
        dto.setEmail(dto.getEmail().toLowerCase());
        Long id = dao.create(dto);
        dto.setId(id);
        return dto;
    }

    @Override
    public Admin update(Admin dto, Long id) {
        dao.update(dto, id);
        return dto;
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
