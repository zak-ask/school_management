package com.example.schoolapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Join Table.
 */
public class FiliereSemestre {
    private Integer id;
    private Filiere filiere;
    private Semestre semestre;
}
