package com.example.schoolapp.dao;

import com.example.schoolapp.config.SingeltonConnection;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.model.Admin;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.utils.PageRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private final Connection conn = SingeltonConnection.getConnection();
    private final UtilisateurDao utilisateurDao = new UtilisateurDao();
    public Admin findById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admins a JOIN utilisateurs u ON a.utilisateur_id = u.id WHERE a.id =?");
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Admin admin = new Admin();
                admin.setId(rs.getLong("id"));
                admin.setNom(rs.getString("nom"));
                admin.setPrenom(rs.getString("prenom"));
                admin.setEmail(rs.getString("email"));

                return admin;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public PageDTO<Admin> findAll(PageRequest pageRequest){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admins a JOIN utilisateurs u ON a.utilisateur_id = u.id LIMIT ?,?");
            ps.setLong(1, pageRequest.getOffset());
            ps.setLong(2, pageRequest.getOffset() + pageRequest.getSize());
            ResultSet rs = ps.executeQuery();
            PageDTO<Admin> page = new PageDTO<>();
            while (rs.next()){
                Admin admin = new Admin();
                admin.setId(rs.getLong("id"));
                admin.setNom(rs.getString("nom"));
                admin.setPrenom(rs.getString("prenom"));
                admin.setEmail(rs.getString("email"));
                page.getContent().add(admin);
            }
            return page;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error :"+e.getCause());
        }
    }

    public long create(Admin admin){
        Long createdUserId = null;
        try {
            // Create new user and get its id.
            createdUserId = utilisateurDao.create(admin);
            if (createdUserId == 0){
                throw new RuntimeException("Error while creating user.");
            }
            PreparedStatement ps = conn.prepareStatement("INSERT INTO admins(utilisateur_id) VALUES(?)");
            ps.setLong(1, createdUserId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Failed to insert admin");
            }
            return createdUserId;

        }catch (SQLException e){
            if (createdUserId != null) utilisateurDao.delete(createdUserId);
            e.printStackTrace();
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int update(Filiere filiere){
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE admins SET libelle =?1 , discription=?2, WHERE id=?3");
            ps.setString(1, filiere.getLibelle());
            ps.setString(2, filiere.getDescription());
            ps.setLong(3, filiere.getId());
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int delete(Filiere filiere) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM admins WHERE id = ?1");
            ps.setString(1, filiere.getLibelle());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error :" + e.getCause());
        }
    }
}
