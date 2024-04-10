package com.example.schoolapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Table 'admins'
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Utilisateur {
    private Long utilisateurId;
}
