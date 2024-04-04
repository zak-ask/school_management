package com.example.schoolapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Table 'utilisateurs'
 */
public class Utilisateur {
    private Long id;
    private String nom;
    private String prenom;
    private String password;
    private String email;
}