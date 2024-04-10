package com.example.schoolapp.dao;

import com.example.schoolapp.config.SingeltonConnection;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.model.Utilisateur;
import com.example.schoolapp.utils.PageRequest;

import java.sql.*;

public class UtilisateurDao {

    private final Connection conn = SingeltonConnection.getConnection();
    public Utilisateur findByEmail(String email){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM utilisateur WHERE email like ?");
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
            PreparedStatement ps1 = conn.prepareStatement("INSERT INTO utilisateurs(nom, prenom, email, password) VALUES(?,?,?,?)" , Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, utilisateur.getNom());
            ps1.setString(2, utilisateur.getPrenom());
            ps1.setString(3, utilisateur.getEmail());
            ps1.setString(4, utilisateur.getPassword());
            // Retrieve the generated primary key
            int aff = ps1.executeUpdate();
            if (aff > 0){
                try (ResultSet keys = ps1.getGeneratedKeys()) {
                    if((keys.next())){
                         return keys.getLong(1);
                    }
                }
            }else{
                throw new RuntimeException("Error: user not created");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error :"+e.getCause());
        }
        return null;
    }
    public int update(Utilisateur utilisateur, Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE utilisateurs SET nom =?, prenom=? WHERE id=?");
            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getPrenom());
            ps.setLong(3, id);
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int delete(Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM utilisateurs WHERE id = ?");
            ps.setLong(1, id);
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
}
