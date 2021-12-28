package com.doitgeek.onlineclassified.occategoryservice.api;

import com.doitgeek.onlineclassified.occategoryservice.constant.ApiStatus;
import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.doitgeek.onlineclassified.occategoryservice.dto.CategoryDTO;
import com.doitgeek.onlineclassified.occategoryservice.entity.Category;
import com.doitgeek.onlineclassified.occategoryservice.exception.CategoryNotFoundException;
import com.doitgeek.onlineclassified.occategoryservice.model.ApiResponseModel;
import com.doitgeek.onlineclassified.occategoryservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create category
    @PostMapping
    public ResponseEntity<ApiResponseModel<Category>> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category createdCategory = categoryService.createCategory(categoryDTO);
        ApiResponseModel<Category> apiResponseModel = new ApiResponseModel<>(createdCategory, ApiStatus.SUCCESS.getStatus());
        return new ResponseEntity<>(apiResponseModel, HttpStatus.CREATED);
    }

    // select single category
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseModel<Category>> getCategoryById(@PathVariable @Min(1) Long id) {
        Category category = categoryService.findById(id).orElseThrow(() ->
                new CategoryNotFoundException(String.format(ErrorMessage.CATEGORY_NOT_FOUND, id)));
        ApiResponseModel<Category> apiResponseModel = new ApiResponseModel<>(category, ApiStatus.SUCCESS.getStatus());
        return ResponseEntity.ok(apiResponseModel);
    }

    // select all categories
    @GetMapping
    public ResponseEntity<ApiResponseModel<List<Category>>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        ApiResponseModel<List<Category>> apiResponseModel = new ApiResponseModel<>(categories, ApiStatus.SUCCESS.getStatus());
        return ResponseEntity.ok(apiResponseModel);
    }

    // update single category
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseModel<Category>> updateCategoryById(@PathVariable @Min(1) Long id,
                                                                         @Valid @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = categoryService.updateCategoryById(id, categoryDTO);
        ApiResponseModel<Category> apiResponseModel = new ApiResponseModel<>(updatedCategory, ApiStatus.SUCCESS.getStatus());
        return ResponseEntity.ok(apiResponseModel);
    }

    // delete single category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
