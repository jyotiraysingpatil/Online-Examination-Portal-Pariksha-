package com.app.services;

import java.util.List;

import com.app.DTO.CategoryDto;
import com.app.entities.Categories;

public interface CategoryService {
	CategoryDto addNew(CategoryDto categoryDto);

Categories update(Categories category);
List<Categories> getAll();
void  delete(Long catId);
Categories getById(Long catId);
}
