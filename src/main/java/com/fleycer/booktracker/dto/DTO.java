package com.fleycer.booktracker.dto;

import com.fleycer.booktracker.entities.Model;

public interface DTO<T extends Model>  {
    T convertToModel();
}
