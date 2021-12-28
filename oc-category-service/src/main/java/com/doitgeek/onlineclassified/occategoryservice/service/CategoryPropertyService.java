package com.doitgeek.onlineclassified.occategoryservice.service;

import com.doitgeek.onlineclassified.occategoryservice.dto.CategoryPropertyDTO;
import com.doitgeek.onlineclassified.occategoryservice.entity.CategoryProperty;

import java.util.List;
import java.util.Optional;

public interface CategoryPropertyService extends GenericService<CategoryProperty, Long> {
    Optional<CategoryProperty> findByIdAndCategoryId(Long id, Long categoryId);
    List<CategoryProperty> findAllByCategoryId(Long categoryId);
    CategoryProperty createCategoryProperty(Long categoryId, CategoryPropertyDTO categoryPropertyDTO);
    CategoryProperty updateCategoryProperty(Long id, Long categoryId, CategoryPropertyDTO categoryPropertyDTO);
    void deleteByIdAndCategoryId(Long id, Long categoryId);
}
