package com.example.schoolapp.metier.impl;

import com.example.schoolapp.dao.ModuleDao;
import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.model.Module;
import com.example.schoolapp.utils.PageRequest;

public class ModuleMetierImpl implements IModuleMetier {
    private final ModuleDao dao= new ModuleDao();
    @Override
    public Module get(Long id) {
        return dao.findById(id);
    }

    @Override
    public PageDTO<Module> page(int page, int size) {
        if (page > 0) page -= 1;
        if (page < 0) page = 0;
        return dao.page(PageRequest.builder().page(page).size(size).build());
    }

    @Override
    public Module create(Module dto) {
        dao.create(dto);
        return dto;
    }

    @Override
    public Module update(Module dto, Long id) {
        dto.setId(id);
        dao.update(dto);
        return dto;
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
