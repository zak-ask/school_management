package com.example.schoolapp.metier;

import com.example.schoolapp.model.Utilisateur;

public interface ICompteMetier extends ICrudMetier<Utilisateur> {
    void login(String email, String password);
    void logout();
}
