package com.doitgeek.onlineclassified.occategoryservice.service;

import com.doitgeek.onlineclassified.occategoryservice.dto.CategoryDTO;
import com.doitgeek.onlineclassified.occategoryservice.entity.Category;

import java.util.Optional;

public interface CategoryService extends GenericService<Category, Long> {
    Optional<Category> findCategoryByName(String categoryName);
    Category createCategory(CategoryDTO categoryDTO);
    Category updateCategoryById(Long id, CategoryDTO categoryDTO);
}
