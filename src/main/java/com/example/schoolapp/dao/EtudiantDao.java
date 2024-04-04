package com.example.schoolapp.dao;

import com.example.schoolapp.config.SingeltonConnection;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.model.Utilisateur;
import com.example.schoolapp.utils.PageRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EtudiantDao {
    private final Connection conn = SingeltonConnection.getConnection();
    private final UtilisateurDao utilisateurDao = new UtilisateurDao();
    public Etudiant findById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM etudiants WHERE id=?1");
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getLong("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setCin(rs.getString("cin"));
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public PageDTO<Etudiant> findAll(PageRequest pageRequest){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM etudiants LIMIT ?1,?2");
            ps.setLong(1, pageRequest.getOffset());
            ps.setLong(2, pageRequest.getOffset() + pageRequest.getSize());
            ResultSet rs = ps.executeQuery();
            PageDTO<Etudiant> etudiantPage = new PageDTO<>();
            while (rs.next()){
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getLong("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setCin(rs.getString("cin"));
                etudiant.setAge(rs.getInt("age"));
                etudiant.setAge(rs.getInt("age"));
                etudiantPage.getContent().add(etudiant);

            }
            return etudiantPage;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }

    public Etudiant create(Etudiant etudiant){
        try {
            // Create new user and get its id.
            Long userId = utilisateurDao.create(etudiant);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO etudiants(cin, age,utilisateur_id) VALUES(?1,?2,?3)");
            ps.setString(1, etudiant.getCin());
            ps.setInt(2, etudiant.getAge());
            ps.setLong(3, userId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0){
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        etudiant.setId(generatedKeys.getLong("id"));
                    } else {
                        throw new RuntimeException("Failed to retrieve generated ID.");
                    }
                }
            }
            throw new RuntimeException("Failed to insert student");

        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int update(Etudiant etudiant, Long id){
        try {
            Utilisateur utilisateur = findJoinedUser(id);
            utilisateurDao.update(etudiant, utilisateur.getId());
            PreparedStatement ps2 = conn.prepareStatement("UPDATE etudiants SET age =?1, cin=?2 WHERE id=?3");
            ps2.setString(1, etudiant.getCin());
            ps2.setInt(2, etudiant.getAge());
            ps2.setLong(3, etudiant.getId());
            return ps2.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int delete(Long id){
        try {
            Utilisateur joinedUser = findJoinedUser(id);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM etudiants WHERE id = ?1");
            ps.setLong(1, id);
            utilisateurDao.delete(joinedUser.getId());

            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public Utilisateur findJoinedUser(Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM utilisateurs s " +
                    "LEFT JOIN etudiants e ON s.id = e.utilisateur_id  WHERE s.id=?1");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return Utilisateur.builder().id(rs.getLong("id"))
                        .nom(rs.getString("nom"))
                        .prenom(rs.getString("prenom"))
                        .build();
            }
            throw new RuntimeException("User not found.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
