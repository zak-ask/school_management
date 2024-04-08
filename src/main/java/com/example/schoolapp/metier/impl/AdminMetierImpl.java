package com.example.schoolapp.metier.impl;

import com.example.schoolapp.dao.AdminDao;
import com.example.schoolapp.model.Admin;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.metier.IAdminMetier;

public class AdminMetierImpl implements IAdminMetier {
    private final AdminDao dao;
    public AdminMetierImpl(){
        dao = new AdminDao();
    }

    @Override
    public Admin get(Long id) {
        return null;
    }

    @Override
    public PageDTO<Admin> page(int page, int size) {
        return null;
    }

    @Override
    public Admin create(Admin dto) {
        Long id = dao.create(dto);
        dto.setId(id);
        return dto;
    }

    @Override
    public Admin update(Admin dto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
