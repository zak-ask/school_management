package com.example.schoolapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageDTO<T> {
    List<T> content = new ArrayList<>();
    int page;
    int size;

}
