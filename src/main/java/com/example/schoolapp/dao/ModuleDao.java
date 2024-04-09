package com.example.schoolapp.dao;

import com.example.schoolapp.config.SingeltonConnection;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.model.Module;
import com.example.schoolapp.utils.PageRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleDao {
    private final Connection conn = SingeltonConnection.getConnection();
    public Module findByStudentId(Long studentId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM modules WHERE etudiant_id=?");
            ps.setLong(1,studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                return Module.builder()
                        .id(rs.getLong("id"))
                        .description(rs.getString("description"))
                        .libelle(rs.getString("libelle"))
                        .semestre(rs.getString("semestre"))
                        .filiere(Filiere.builder().id(rs.getLong("filiere_id")).build())
                        .build();
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public Module findById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM modules WHERE id=?");
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                return Module.builder()
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
    public PageDTO<Module> page(PageRequest pageRequest){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM modules LIMIT ?,?");
            ps.setLong(1, pageRequest.getOffset());
            ps.setLong(2, pageRequest.getOffset() + pageRequest.getSize());
            ResultSet rs = ps.executeQuery();
            PageDTO<Module> pageDTO = new PageDTO<>();
            pageDTO.setPage(pageRequest.getPage());
            pageDTO.setSize(pageRequest.getSize());
            while (rs.next()){

                pageDTO.getContent().add(Module.builder()
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
    public List<Module> findAll(){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM modules");
            ResultSet rs = ps.executeQuery();
            List<Module> filiereList = new ArrayList<>();
            while (rs.next()){

                filiereList.add(Module.builder()
                        .id(rs.getLong("id"))
                        .description(rs.getString("description"))
                        .libelle(rs.getString("libelle"))
                        .semestre(rs.getString("semestre"))
                        .filiere(Filiere.builder().id(rs.getLong("filiere_id")).build())
                        .build());
            }
            return filiereList;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }

    public int create(Module module){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO modules(libelle, description,semestre,filiere_id) VALUES(?,?,?,?)");
            ps.setString(1, module.getLibelle());
            ps.setString(2, module.getDescription());
            ps.setString(3, module.getSemestre());
            ps.setLong(4, module.getFiliere().getId());
            return ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int delete(Module module){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM modules WHERE id = ?");
            ps.setString(1, module.getLibelle());
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
}
