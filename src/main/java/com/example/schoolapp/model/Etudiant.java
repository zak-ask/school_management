package com.example.schoolapp.model;

import java.util.List;

/**
 * table 'Etudiants'
 */
public class Etudiant extends Utilisateur {
    private Integer id;
    private String cin;
    private String sexe;
    private String phone;
    private Filiere filiere;
    private List<Note> notes;
}
