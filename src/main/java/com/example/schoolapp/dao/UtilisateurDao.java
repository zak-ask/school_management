package com.example.schoolapp.dao;

import com.example.schoolapp.config.SingeltonConnection;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.model.Utilisateur;
import com.example.schoolapp.utils.PageRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UtilisateurDao {

    private final Connection conn = SingeltonConnection.getConnection();
    public Utilisateur findByEmail(String email){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM utilisateur WHERE email=?1");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Utilisateur utilisateur=  new Utilisateur();
                utilisateur.setId(rs.getLong("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setEmail(rs.getString("emial"));
                return utilisateur;
            }
            throw new RuntimeException("No user found.");
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }

    public Long create(Utilisateur utilisateur){
        try {
            PreparedStatement ps1 = conn.prepareStatement("INSERT INTO utilisateurs(nom,prenom,email,password) VALUES(?1,?2,?3,?4)");
            ps1.setString(1, utilisateur.getNom());
            ps1.setString(2, utilisateur.getPrenom());
            ps1.setString(3, utilisateur.getEmail());
            ps1.setString(4, utilisateur.getPassword());
            int affectedRows = ps1.executeUpdate();
            // Retrieve the generated primary key
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps1.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong("id");
                    } else {
                        throw new RuntimeException("Failed to retrieve generated ID.");
                    }
                }
            } else {
                throw new RuntimeException("Insertion failed, no rows affected.");
            }
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int update(Utilisateur utilisateur, Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE utilisateurs SET nom =?1 , prenom=?2, email=?3 WHERE id=?4");
            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getPrenom());
            ps.setString(3, utilisateur.getEmail());
            ps.setLong(4, id);
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int delete(Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM utilisateurs WHERE id = ?1");
            ps.setLong(1, id);
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
}
