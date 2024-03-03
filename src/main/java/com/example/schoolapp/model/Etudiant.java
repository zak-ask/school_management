package com.example.schoolapp.model;

import java.util.List;

public class Etudiant extends Personne {
    private Integer id;
    private String cin;
    private String sexe;
    private String phone;
    private Filiere filiere;
    private List<Note> notes;
}
