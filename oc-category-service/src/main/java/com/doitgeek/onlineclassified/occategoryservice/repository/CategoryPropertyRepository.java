package com.doitgeek.onlineclassified.occategoryservice.repository;

import com.doitgeek.onlineclassified.occategoryservice.entity.CategoryProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryPropertyRepository extends JpaRepository<CategoryProperty, Long> {
    List<CategoryProperty> findAllByCategoryId(Long categoryId);
    Optional<CategoryProperty> findByIdAndCategoryId(Long id, Long categoryId);
}
