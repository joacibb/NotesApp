package ar.com.noteschallenge.service.impl;

import ar.com.noteschallenge.DTO.CategoryDTO;
import ar.com.noteschallenge.model.Category;
import ar.com.noteschallenge.model.Note;

import java.util.List;

public interface CategoryService {
    Category findById(Long idCat);
    List<Category> findAll();
    Category update(Long idCat, CategoryDTO catDTO);

    Long findByName(String categoryName);

    void save(Category category);

    Category clone(Category category);

    List<Category> listFilter(List<Category> notFilterList);
}
