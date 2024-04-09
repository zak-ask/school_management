package com.example.schoolapp.metier.impl;

import com.example.schoolapp.dto.PageDTO;
import com.example.schoolapp.metier.IModuleMetier;
import com.example.schoolapp.model.Module;

public class ModuleMetierImpl implements IModuleMetier {
    @Override
    public Module get(Long id) {
        return null;
    }

    @Override
    public PageDTO<Module> page(int page, int size) {
        return null;
    }

    @Override
    public Module create(Module dto) {
        return null;
    }

    @Override
    public Module update(Module dto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
