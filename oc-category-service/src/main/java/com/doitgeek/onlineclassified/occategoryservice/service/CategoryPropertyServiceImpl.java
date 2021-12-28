package com.doitgeek.onlineclassified.occategoryservice.service;

import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.doitgeek.onlineclassified.occategoryservice.dto.CategoryPropertyDTO;
import com.doitgeek.onlineclassified.occategoryservice.entity.Category;
import com.doitgeek.onlineclassified.occategoryservice.entity.CategoryProperty;
import com.doitgeek.onlineclassified.occategoryservice.exception.CategoryNotFoundException;
import com.doitgeek.onlineclassified.occategoryservice.exception.CategoryPropertyNotFoundException;
import com.doitgeek.onlineclassified.occategoryservice.exception.CategoryServiceException;
import com.doitgeek.onlineclassified.occategoryservice.repository.CategoryPropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryPropertyServiceImpl implements CategoryPropertyService {

    private final CategoryPropertyRepository categoryPropertyRepository;
    private final CategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryPropertyServiceImpl.class);

    @Autowired
    public CategoryPropertyServiceImpl(CategoryPropertyRepository categoryPropertyRepository, CategoryService categoryService) {
        this.categoryPropertyRepository = categoryPropertyRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Optional<CategoryProperty> findById(Long id) {
        return categoryPropertyRepository.findById(id);
    }

    @Override
    public List<CategoryProperty> findAll() {
        return categoryPropertyRepository.findAll();
    }

    @Override
    public CategoryProperty save(CategoryProperty categoryProperty) {
        return categoryPropertyRepository.save(categoryProperty);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("Inside deleteById...");
        CategoryProperty categoryPropertyToDelete = findById(id).orElseThrow(() ->
                new CategoryPropertyNotFoundException(String.format(ErrorMessage.CATEGORY_PROP_NOT_FOUND, id)));

        LOGGER.info("categoryPropertyToDelete: {}", categoryPropertyToDelete);

        categoryPropertyRepository.delete(categoryPropertyToDelete);
    }

    @Override
    public Optional<CategoryProperty> findByIdAndCategoryId(Long id, Long categoryId) {
        return categoryPropertyRepository.findByIdAndCategoryId(id, categoryId);
    }

    @Override
    public List<CategoryProperty> findAllByCategoryId(Long categoryId) {
        return categoryPropertyRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public void deleteByIdAndCategoryId(Long id, Long categoryId) {
        LOGGER.info("Inside deleteByIdAndCategoryId...");
        CategoryProperty categoryPropertyToDelete = findByIdAndCategoryId(id, categoryId).orElseThrow(() ->
                new CategoryPropertyNotFoundException(String.format(ErrorMessage.PROP_FOR_CAT_ID_NOT_FOUND, id, categoryId)));

        LOGGER.info("categoryPropertyToDelete: {}", categoryPropertyToDelete);

        categoryPropertyRepository.delete(categoryPropertyToDelete);
    }

    @Override
    public CategoryProperty createCategoryProperty(Long categoryId, CategoryPropertyDTO categoryPropertyDTO) {
        LOGGER.info("Inside createCategoryProperty...");

        // Check if provided category ids matches
        if (!categoryId.equals(categoryPropertyDTO.getCategoryId())) {
            throw new CategoryServiceException(String.format(ErrorMessage.MISMATCH_CATEGORY_IDS, categoryId, categoryPropertyDTO.getCategoryId()));
        }

        // Check if category id exist
        Optional<Category> optionalCategoryProperty = categoryService.findById(categoryPropertyDTO.getCategoryId());
        if (optionalCategoryProperty.isEmpty()) {
            throw new CategoryNotFoundException(String.format(ErrorMessage.CATEGORY_NOT_FOUND, categoryPropertyDTO.getCategoryId()));
        }

        CategoryProperty categoryPropertyToCreate = new CategoryProperty();
        categoryPropertyToCreate.setCategoryId(categoryPropertyDTO.getCategoryId());
        categoryPropertyToCreate.setPropertyName(categoryPropertyDTO.getPropertyName());
        categoryPropertyToCreate.setPropertyUnit(categoryPropertyDTO.getPropertyUnit());
        categoryPropertyToCreate.setIsMandatory(categoryPropertyDTO.getIsMandatory());
        categoryPropertyToCreate.setPossibleValues(categoryPropertyDTO.getPossibleValues());

        LOGGER.info("categoryPropertyToCreate: {}", categoryPropertyToCreate);

        return save(categoryPropertyToCreate);
    }

    @Override
    public CategoryProperty updateCategoryProperty(Long id, Long categoryId, CategoryPropertyDTO categoryPropertyDTO) {
        LOGGER.info("Inside updateCategoryProperty...");

        // Check if categoryProperty id exists
        CategoryProperty categoryPropertyToUpdate = findByIdAndCategoryId(id, categoryId).orElseThrow(() ->
                new CategoryPropertyNotFoundException(String.format(ErrorMessage.PROP_FOR_CAT_ID_NOT_FOUND, id, categoryId)));

        // Check if category id is valid.
        if (!categoryPropertyToUpdate.getCategoryId().equals(categoryPropertyDTO.getCategoryId())) {
            Optional<Category> optionalCategory = categoryService.findById(categoryPropertyDTO.getCategoryId());
            if (optionalCategory.isEmpty()) {
                throw new CategoryNotFoundException(String.format(ErrorMessage.CATEGORY_NOT_FOUND, categoryPropertyDTO.getCategoryId()));
            }
        }

        categoryPropertyToUpdate.setCategoryId(categoryPropertyDTO.getCategoryId());
        categoryPropertyToUpdate.setPropertyName(categoryPropertyDTO.getPropertyName());
        categoryPropertyToUpdate.setPropertyUnit(categoryPropertyDTO.getPropertyUnit());
        categoryPropertyToUpdate.setIsMandatory(categoryPropertyDTO.getIsMandatory());
        categoryPropertyToUpdate.setPossibleValues(categoryPropertyDTO.getPossibleValues());

        LOGGER.info("categoryPropertyToUpdate: {}", categoryPropertyToUpdate);

        return save(categoryPropertyToUpdate);
    }
}
