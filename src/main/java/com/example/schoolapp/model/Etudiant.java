package com.example.schoolapp.model;

import lombok.*;

import java.util.List;

/**
 * table 'Etudiants'
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Etudiant extends Utilisateur {
    private Long utilisateurId;
    private String cin;
    private Filiere filiere;
    private List<Note> notes;
}
