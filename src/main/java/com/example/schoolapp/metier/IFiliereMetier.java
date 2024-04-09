package com.example.schoolapp.metier;
import com.example.schoolapp.model.Filiere;

import java.util.List;

public interface IFiliereMetier extends ICrudMetier<Filiere> {

    List<Filiere> getAll();

    Filiere getByStudentId(Long studentId);
}
