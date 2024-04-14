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
    public Module findById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT m.id as m_id, m.description as m_description, m.libelle as m_libelle, " +
                    " m.semestre as m_semestre, f.id as f_id, f.libelle as f_libelle," +
                    "f.description as f_description FROM modules m " +
                    "LEFT JOIN filieres f ON f.id= m.filiere_id WHERE m.id=?");
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Filiere filiere = Filiere.builder()
                        .id(rs.getLong("f_id"))
                        .libelle(rs.getString("f_libelle"))
                        .description(rs.getString("f_description")).build();
                return Module.builder()
                        .id(rs.getLong("m_id"))
                        .description(rs.getString("m_description"))
                        .libelle(rs.getString("m_libelle"))
                        .semestre(rs.getString("m_semestre"))
                        .filiere(filiere)
                        .build();
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public PageDTO<Module> page(PageRequest pageRequest){
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT m.id as m_id, m.description as m_description, m.libelle as m_libelle, " +
                            " m.semestre as m_semestre, f.id as f_id, f.libelle as f_libelle," +
                            "f.description as f_description FROM modules m " +
                            "LEFT JOIN filieres f ON f.id= m.filiere_id "
                    +"ORDER BY f.libelle LIMIT ?,?");
            ps.setLong(1, pageRequest.getOffset());
            ps.setLong(2, pageRequest.getOffset() + pageRequest.getSize());
            ResultSet rs = ps.executeQuery();
            PageDTO<Module> pageDTO = new PageDTO<>();
            pageDTO.setPage(pageRequest.getPage());
            pageDTO.setSize(pageRequest.getSize());
            while (rs.next()){
                Filiere filiere = Filiere.builder()
                        .id(rs.getLong("f_id"))
                        .libelle(rs.getString("f_libelle"))
                        .description(rs.getString("f_description")).build();
                Module module = Module.builder()
                        .id(rs.getLong("m_id"))
                        .description(rs.getString("m_description"))
                        .libelle(rs.getString("m_libelle"))
                        .semestre(rs.getString("m_semestre"))
                        .filiere(filiere)
                        .build();
                pageDTO.getContent().add(module);
            }
            return pageDTO;
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
    public int update(Module module, Long id) {
        try {
            PreparedStatement ps2 = conn.prepareStatement("UPDATE modules SET libelle =? , description =?, semestre=?, filiere_id =?  WHERE id=?");
            ps2.setString(1, module.getLibelle());
            ps2.setString(2, module.getDescription());
            ps2.setString(3, module.getSemestre());
            ps2.setLong(4, module.getFiliere().getId());
            ps2.setLong(5,id);
            return ps2.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
    public int delete(Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM modules WHERE id = ?");
            ps.setLong(1, id);
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
}
