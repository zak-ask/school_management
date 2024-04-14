package com.example.schoolapp.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Table 'filieres'
 */
public class Filiere {
    private Long id;
    private String libelle;
    private String description;
    private List<Etudiant> etudiantList;
    private List<Module> moduleList;
}
