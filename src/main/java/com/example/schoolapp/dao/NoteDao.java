package com.example.schoolapp.dao;

import com.example.schoolapp.config.SingeltonConnection;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.model.Etudiant;
import com.example.schoolapp.model.Filiere;
import com.example.schoolapp.model.Module;
import com.example.schoolapp.model.Note;
import com.example.schoolapp.utils.PageRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {
    private final Connection conn = SingeltonConnection.getConnection();
    public Note findByStudentId(Long studentId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT n.id, n.note, e.id as et_id, " +
                    "e.nom as et_nom, e.prenom as et_prenom, m.id as module_id, m.libelle as module_libelle " +
                    "FROM note n LEFT JOIN etudiants e ON e.id = n.etudiant_id" +
                    " LEFT JOIN modules m ON m.id = n.module_id " +
                    " WHERE f.id=?");
            ps.setLong(1,studentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                return Note.builder()
                        .id(rs.getLong("id"))
                        .note(rs.getDouble("note"))
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
    public PageDTO<Note> page(PageRequest pageRequest){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT n.id as id, n.note as note, e.id as et_id, " +
                    "e.nom as et_nom, e.prenom as et_prenom, m.id as module_id, m.libelle as module_libelle " +
                            "FROM note n LEFT JOIN etudiants e ON e.id = n.etudiant_id" +
                            " LEFT JOIN modules m ON m.id = n.module_id " +
                            " WHERE f.id=? ORDER BY f.libelle LIMIT ?,?");
            ps.setLong(1, pageRequest.getOffset());
            ps.setLong(2, pageRequest.getOffset() + pageRequest.getSize());
            ResultSet rs = ps.executeQuery();
            PageDTO<Note> pageDTO = new PageDTO<>();
            pageDTO.setPage(pageRequest.getPage());
            pageDTO.setSize(pageRequest.getSize());
            while (rs.next()){
                Etudiant etudiant = new Etudiant();
                etudiant.setNom(rs.getString("et_nom"));
                etudiant.setPrenom(rs.getString("et_prenom"));
                pageDTO.getContent().add(Note.builder()
                        .id(rs.getLong("id"))
                        .note(rs.getDouble("note"))
                        .etudiant(etudiant)
                        .module(Module.builder()
                                .id(rs.getLong("module_id"))
                                .libelle(rs.getString("module_libelle"))
                                .build())
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

    public List<Note> findAllByStudent(Etudiant etudiant) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT m.id AS module_id, " +
                    "       m.libelle AS module_libelle, " +
                    "       f.id AS filiere_id," +
                    "       f.libelle AS filiere_libelle, " +
                    "       n.note AS note , n.id AS note_id " +
                    " FROM modules m " +
                    " LEFT JOIN notes n ON m.id = n.module_id " +
                    " AND n.etudiant_id = ?" +
                    " LEFT JOIN filieres f ON m.filiere_id = f.id ORDER BY m.libelle ");
            ps.setLong(1,etudiant.getId());
            ResultSet rs = ps.executeQuery();
            List<Note> list = new ArrayList<>();
            while (rs.next()){
                list.add(Note.builder()
                        .id(rs.getLong("note_id"))
                        .note(rs.getDouble("note"))
                        .etudiant(etudiant)
                        .module(Module.builder()
                                .id(rs.getLong("module_id"))
                                .libelle(rs.getString("module_libelle"))
                                .filiere(Filiere.builder()
                                        .libelle(rs.getString("filiere_libelle"))
                                        .id(rs.getLong("filiere_id"))
                                        .build())
                                .build())
                        .build());
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException("Error :"+e.getCause());
        }
    }
}
