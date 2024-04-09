package com.example.schoolapp.metier.impl;

import com.example.schoolapp.dao.UtilisateurDao;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.metier.ICompteMetier;
import com.example.schoolapp.model.Utilisateur;
import com.example.schoolapp.utils.PasswordHashing;

public class CompteMetierImpl implements ICompteMetier {
    private final UtilisateurDao dao = new UtilisateurDao();
    @Override
    public void login(String email, String password) {

    }

    @Override
    public void logout() {

    }

    @Override
    public Utilisateur get(Long id) {
        return null;
    }

    @Override
    public PageDTO<Utilisateur> page(int page, int size) {
        return null;
    }

    @Override
    public Utilisateur create(Utilisateur dto) {
        // hash password
        dto.setPrenom(PasswordHashing.hashPassword(dto.getPassword()));
        dao.create(dto);
        return dto;
    }

    @Override
    public Utilisateur update(Utilisateur dto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
