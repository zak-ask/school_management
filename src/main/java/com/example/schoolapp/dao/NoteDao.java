package com.example.schoolapp.dao;

import com.example.schoolapp.config.SingeltonConnection;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.utils.PageRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {
    private final Connection conn = SingeltonConnection.getConnection();
    public Filiere findByStudentId(Long studentId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT f.* FROM filieres f LEFT JOIN etudiants e ON f.id = e.filiere_id WHERE f.id=?");
            ps.setLong(1,studentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

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
    public Filiere findById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM filieres WHERE id=?");
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
    public PageDTO<Filiere> page(PageRequest pageRequest){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM filieres LIMIT ?,?");
            ps.setLong(1, pageRequest.getOffset());
            ps.setLong(2, pageRequest.getOffset() + pageRequest.getSize());
            ResultSet rs = ps.executeQuery();
            PageDTO<Filiere> pageDTO = new PageDTO<>();
            pageDTO.setPage(pageRequest.getPage());
            pageDTO.setSize(pageRequest.getSize());
            while (rs.next()){

                pageDTO.getContent().add(Filiere.builder()
                        .id(rs.getLong("id"))
                        .description(rs.getString("description"))
                        .libelle(rs.getString("libelle"))
                        .build());
            }
            return pageDTO;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public List<Filiere> findAll(){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM filieres");
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

    public int create(Filiere filiere){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO filieres(libelle, description) VALUES(?,?)");
            ps.setString(1, filiere.getLibelle());
            ps.setString(2, filiere.getDescription());
            return ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int delete(Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM filieres WHERE id = ?");
            ps.setLong(1, id);
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }

    public int update(Filiere filiere) {
        try {
            PreparedStatement ps2 = conn.prepareStatement("UPDATE filieres SET libelle =? , description =?  WHERE id=?");
            ps2.setString(1, filiere.getLibelle());
            ps2.setString(2, filiere.getDescription());
            ps2.setLong(3, filiere.getId());
            return ps2.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
}
