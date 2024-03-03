package com.example.schoolapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {
    private Integer id;
    private String libelle;
    private String description;
    private List<Etudiant> etudiantList;
    private List<FiliereSemestre> filiereSemestreList;
}
