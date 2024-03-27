package com.example.schoolapp.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageRequest {
    int page;
    int size;
    int offset;

    public int getOffset() {
        return page * size;
    }
}
