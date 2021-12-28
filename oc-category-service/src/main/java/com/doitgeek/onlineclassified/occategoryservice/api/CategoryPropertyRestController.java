package com.doitgeek.onlineclassified.occategoryservice.api;

import com.doitgeek.onlineclassified.occategoryservice.constant.ApiStatus;
import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.doitgeek.onlineclassified.occategoryservice.dto.CategoryPropertyDTO;
import com.doitgeek.onlineclassified.occategoryservice.entity.CategoryProperty;
import com.doitgeek.onlineclassified.occategoryservice.exception.CategoryPropertyNotFoundException;
import com.doitgeek.onlineclassified.occategoryservice.model.ApiResponseModel;
import com.doitgeek.onlineclassified.occategoryservice.service.CategoryPropertyService;
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
@RequestMapping("/categories/{categoryId}/properties")
@Validated
public class CategoryPropertyRestController {

    private final CategoryPropertyService categoryPropertyService;

    @Autowired
    public CategoryPropertyRestController(CategoryPropertyService categoryPropertyService) {
        this.categoryPropertyService = categoryPropertyService;
    }

    // create property for a category id
    @PostMapping
    public ResponseEntity<ApiResponseModel<CategoryProperty>> createCategoryProperty(@PathVariable @Min(1) Long categoryId,
                                                                                     @Valid @RequestBody CategoryPropertyDTO categoryPropertyDTO) {
        CategoryProperty createdCategoryProperty = categoryPropertyService.createCategoryProperty(categoryId, categoryPropertyDTO);
        ApiResponseModel<CategoryProperty> apiResponseModel = new ApiResponseModel<>(createdCategoryProperty, ApiStatus.SUCCESS.getStatus());
        return new ResponseEntity<>(apiResponseModel, HttpStatus.CREATED);
    }

    // get single property by property id and category id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseModel<CategoryProperty>> getCategoryPropertyById(@PathVariable @Min(1) Long id,
                                                                                      @PathVariable @Min(1) Long categoryId) {
        CategoryProperty categoryProperty = categoryPropertyService.findByIdAndCategoryId(id, categoryId).orElseThrow(() ->
                new CategoryPropertyNotFoundException(String.format(ErrorMessage.PROP_FOR_CAT_ID_NOT_FOUND, id, categoryId)));
        ApiResponseModel<CategoryProperty> apiResponseModel = new ApiResponseModel<>(categoryProperty, ApiStatus.SUCCESS.getStatus());
        return ResponseEntity.ok(apiResponseModel);
    }

    // get all properties for given category id
    @GetMapping
    public ResponseEntity<ApiResponseModel<List<CategoryProperty>>> getAllCategoryProperties(@PathVariable @Min(1) Long categoryId) {
        List<CategoryProperty> categoryProperties = categoryPropertyService.findAllByCategoryId(categoryId);
        ApiResponseModel<List<CategoryProperty>> apiResponseModel = new ApiResponseModel<>(categoryProperties, ApiStatus.SUCCESS.getStatus());
        return ResponseEntity.ok(apiResponseModel);
    }

    // update property for given category id
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseModel<CategoryProperty>> updatePropertyById(@PathVariable @Min(1) Long id,
                                                                                 @PathVariable @Min(1) Long categoryId,
                                                                                 @Valid @RequestBody CategoryPropertyDTO categoryPropertyDTO) {
        CategoryProperty updatedCategoryProperty = categoryPropertyService.updateCategoryProperty(id, categoryId, categoryPropertyDTO);
        ApiResponseModel<CategoryProperty> apiResponseModel = new ApiResponseModel<>(updatedCategoryProperty, ApiStatus.SUCCESS.getStatus());
        return ResponseEntity.ok(apiResponseModel);
    }

    // delete property
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyById(@PathVariable @Min(1) Long id, @PathVariable @Min(1) Long categoryId) {
        categoryPropertyService.deleteByIdAndCategoryId(id, categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
