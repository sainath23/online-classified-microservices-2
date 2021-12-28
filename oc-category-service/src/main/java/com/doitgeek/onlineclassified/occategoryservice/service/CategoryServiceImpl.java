package com.doitgeek.onlineclassified.occategoryservice.service;

import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.doitgeek.onlineclassified.occategoryservice.dto.CategoryDTO;
import com.doitgeek.onlineclassified.occategoryservice.entity.Category;
import com.doitgeek.onlineclassified.occategoryservice.exception.CategoryNotFoundException;
import com.doitgeek.onlineclassified.occategoryservice.exception.CategoryServiceException;
import com.doitgeek.onlineclassified.occategoryservice.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        Category categoryToDelete = findById(id).orElseThrow(() ->
                new CategoryNotFoundException(String.format(ErrorMessage.CATEGORY_NOT_FOUND, id)));
        categoryRepository.delete(categoryToDelete);
    }

    @Override
    public Optional<Category> findCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryNameIgnoreCase(categoryName);
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        LOGGER.info("inside createCategory...");

        // Check if category name exist
        Optional<Category> optionalCategory = findCategoryByName(categoryDTO.getCategoryName());
        if (optionalCategory.isPresent()) {
            throw new CategoryServiceException(String.format(ErrorMessage.CATEGORY_NAME_EXIST, categoryDTO.getCategoryName()));
        }

        // Check if parent category id is valid
        if (categoryDTO.getParentCategoryId() != null) {
            optionalCategory = findById(categoryDTO.getParentCategoryId());
            if (optionalCategory.isEmpty()) {
                throw new CategoryNotFoundException(String.format(ErrorMessage.PARENT_CATEGORY_NOT_FOUND, categoryDTO.getParentCategoryId()));
            }
        }

        Category categoryToCreate = new Category();
        categoryToCreate.setCategoryName(categoryDTO.getCategoryName());
        categoryToCreate.setParentCategoryId(categoryDTO.getParentCategoryId());
        categoryToCreate.setMaxImagesAllowed(categoryDTO.getMaxImagesAllowed());
        categoryToCreate.setPostValidityDays(categoryDTO.getPostValidityDays());

        LOGGER.info("categoryToCreate: {}", categoryToCreate);

        return save(categoryToCreate);
    }

    @Override
    public Category updateCategoryById(Long id, CategoryDTO categoryDTO) {
        LOGGER.info("Inside updateCategoryById...");

        Category categoryToUpdate = findById(id).orElseThrow(() ->
                new CategoryNotFoundException(String.format(ErrorMessage.CATEGORY_NOT_FOUND, id)));

        // Check if category name exist
        Optional<Category> optionalCategory;
        if (!categoryToUpdate.getCategoryName().equalsIgnoreCase(categoryDTO.getCategoryName())) {
            optionalCategory = findCategoryByName(categoryDTO.getCategoryName());
            if (optionalCategory.isPresent()) {
                throw new CategoryServiceException(String.format(ErrorMessage.CATEGORY_NAME_EXIST, categoryDTO.getCategoryName()));
            }
        }

        // Parent category id should not be same as category to update
        if (categoryToUpdate.getParentCategoryId() != null && categoryToUpdate.getId().equals(categoryDTO.getParentCategoryId())) {
            throw new CategoryServiceException("Parent category id should not be same as updating category id.");
        }

        // Check if parent category id exist
        if (categoryToUpdate.getParentCategoryId() != null && categoryDTO.getParentCategoryId() != null
                && !categoryToUpdate.getParentCategoryId().equals(categoryDTO.getParentCategoryId())) {
            optionalCategory = findById(categoryDTO.getParentCategoryId());
            if (optionalCategory.isEmpty()) {
                throw new CategoryServiceException(String.format(ErrorMessage.PARENT_CATEGORY_NOT_FOUND, categoryDTO.getParentCategoryId()));
            }
        }

        categoryToUpdate.setCategoryName(categoryDTO.getCategoryName());
        categoryToUpdate.setParentCategoryId(categoryDTO.getParentCategoryId());
        categoryToUpdate.setMaxImagesAllowed(categoryDTO.getMaxImagesAllowed());
        categoryToUpdate.setPostValidityDays(categoryDTO.getPostValidityDays());

        LOGGER.info("categoryToUpdate: {}", categoryToUpdate);

        return save(categoryToUpdate);
    }
}
