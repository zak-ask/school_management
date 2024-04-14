package com.example.schoolapp.dao;

import com.example.schoolapp.config.SingeltonConnection;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.model.Filiere;
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM etudiants e JOIN utilisateurs u ON e.utilisateur_id = u.id WHERE e.id =?");
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getLong("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setEmail(rs.getString("email"));
                etudiant.setCin(rs.getString("cin"));
                etudiant.setFiliere(Filiere.builder().id(rs.getLong("filiere_id")).build());
                return etudiant;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public PageDTO<Etudiant> findAll(PageRequest pageRequest){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM etudiants a JOIN utilisateurs u ON a.utilisateur_id = u.id LIMIT ?,?");
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
                etudiant.setEmail(rs.getString("email"));
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
            Long createdUserId = utilisateurDao.create(etudiant);
            if (createdUserId == 0){
                throw new RuntimeException("Error while creating user.");
            }
            PreparedStatement ps = conn.prepareStatement("INSERT INTO etudiants(cin, filiere_id, utilisateur_id) VALUES(?,?,?)");
            ps.setString(1, etudiant.getCin());
            ps.setLong(2, etudiant.getFiliere().getId());
            ps.setLong(3, createdUserId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Failed to insert student");
            }
            etudiant.setUtilisateurId(createdUserId);
            return etudiant;

        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int update(Etudiant etudiant, Long id){
        try {
            Utilisateur utilisateur = findJoinedUser(id);
            utilisateurDao.update(etudiant, utilisateur.getId());
            PreparedStatement ps2 = conn.prepareStatement("UPDATE etudiants SET cin=? WHERE id=?");
            ps2.setString(1, etudiant.getCin());
            ps2.setLong(2, etudiant.getId());
            return ps2.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int delete(Long id){
        try {
            Utilisateur joinedUser = findJoinedUser(id);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM etudiants WHERE id = ?");
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
                    "LEFT JOIN etudiants e ON s.id = e.utilisateur_id  WHERE e.id=?");
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
