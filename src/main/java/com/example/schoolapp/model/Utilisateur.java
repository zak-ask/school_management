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
 * Table 'utilisateurs'
 */
public abstract class Utilisateur {
    private Integer id;
    private String nom;
    private String prenom;
    private String password;
    private String email;
}
