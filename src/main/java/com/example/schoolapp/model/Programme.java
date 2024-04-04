package com.example.schoolapp.model;

import lombok.*;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Programme {
    private Integer id;
    // date debut du module.
    private Date dateDebut;
    // date fin du module.
    private Date dateFin;
    // many to one
    private Module module;
    // many to one
    private Filiere filiere;
    // many to one
    private Semestre semestre;
}
