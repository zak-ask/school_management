package com.example.schoolapp.model;

import lombok.*;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Programme {
    private Integer id;
    private Date dateDebut;
    private Date dateFin;
    private Module module;
    private Filiere filiere;
    private FiliereSemestre filiereSemestre;
}
