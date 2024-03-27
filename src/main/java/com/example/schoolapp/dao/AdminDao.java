package com.example.schoolapp.dao;

import com.example.schoolapp.config.SingeltonConnection;
import com.example.schoolapp.model.Admin;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.utils.PageRequest;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AdminDao {
    private final Connection conn = SingeltonConnection.getConnection();
    public Filiere findById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE id=?1");
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                return Filiere.builder()
                        .id(rs.getLong("id"))
                        .description(rs.getString("description"))
                        .libelle(rs.getString("libelle"))
                        .build();
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public List<Filiere> findAll(PageRequest pageRequest){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin LIMIT ?1,?2");
            ps.setLong(1, pageRequest.getOffset());
            ps.setLong(1, pageRequest.getOffset() + pageRequest.getSize());
            ResultSet rs = ps.executeQuery();
            List<Filiere> filiereList = new ArrayList<>();
            while (rs.next()){

                filiereList.add(Filiere.builder()
                        .id(rs.getLong("id"))
                        .description(rs.getString("description"))
                        .libelle(rs.getString("libelle"))
                        .build());
            }
            return filiereList;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }

    public int create(Admin admin){
        try {
            // todo create admin should create first row in users table then into admins table.
            PreparedStatement ps = conn.prepareStatement("INSERT INTO utilisateurs(nom, prenom, email,password) VALUES(?1,?2)");
            ps.setString(1, admin.getNom());
            ps.setString(2, admin.getPrenom());
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int update(Filiere filiere){
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE admin SET libelle =?1 , discription=?2, WHERE id=?3");
            ps.setString(1, filiere.getLibelle());
            ps.setString(2, filiere.getDescription());
            ps.setLong(3, filiere.getId());
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int delete(Filiere filiere){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM admin WHERE id = ?1");
            ps.setString(1, filiere.getLibelle());
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
}
