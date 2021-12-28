package com.doitgeek.onlineclassified.occategoryservice.dto;

import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.doitgeek.onlineclassified.occategoryservice.constant.ServiceConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO {
    @NotBlank(message = ErrorMessage.CATEGORY_NAME_NOT_EMPTY)
    @Size(min = ServiceConstant.CATEGORY_NAME_MIN, max = ServiceConstant.CATEGORY_NAME_MAX, message = ErrorMessage.CATEGORY_NAME_SIZE)
    private String categoryName;

    private Long parentCategoryId;

    @NotNull(message = ErrorMessage.MAX_IMAGES_NOT_NULL)
    private Integer maxImagesAllowed;

    @NotNull(message = ErrorMessage.POST_VALIDITY_NOT_NULL)
    private Integer postValidityDays;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getMaxImagesAllowed() {
        return maxImagesAllowed;
    }

    public void setMaxImagesAllowed(Integer maxImagesAllowed) {
        this.maxImagesAllowed = maxImagesAllowed;
    }

    public Integer getPostValidityDays() {
        return postValidityDays;
    }

    public void setPostValidityDays(Integer postValidityDays) {
        this.postValidityDays = postValidityDays;
    }
}
