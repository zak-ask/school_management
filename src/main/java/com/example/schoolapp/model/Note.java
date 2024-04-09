package com.example.schoolapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private Integer id;
    private Double note;
    private Etudiant etudiant;
    private Module module;
}
