package com.example.schoolapp.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module {
    private Long id;
    private String libelle;
    private String description;
    private Filiere filiere;
    private String semestre;
}