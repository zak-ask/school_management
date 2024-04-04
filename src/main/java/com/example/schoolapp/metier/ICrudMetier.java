package com.example.schoolapp.metier;

import com.example.schoolapp.dto.PageDTO;

public interface ICrudMetier<D> {
    D get(Long id);
    PageDTO<D> page(int page, int size);
    D create(D dto);
    D update(D dto, Long id);
    void delete(Long id);

}
