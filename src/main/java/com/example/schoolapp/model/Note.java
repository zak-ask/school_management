package com.example.schoolapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    private Long id;
    private Double note;
    private Etudiant etudiant;
    private Module module;
}
